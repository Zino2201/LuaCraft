package fr.luacraft.core.proxy;

import com.naef.jnlua.LuaState;
import com.naef.jnlua.NativeSupport;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.LoadController;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import fr.luacraft.core.LuaNativeLoader;
import fr.luacraft.core.Luacraft;
import fr.luacraft.core.api.command.LuacraftCommand;
import fr.luacraft.core.api.entity.LuacraftTileEntity;
import fr.luacraft.core.api.libs.LuaLibrary;
import fr.luacraft.core.api.meta.LuaMetaClass;
import fr.luacraft.core.api.network.LuacraftPacketHandler;
import fr.luacraft.core.api.util.LuaTimerManager;
import fr.luacraft.core.gui.LuacraftGuiHandler;
import fr.luacraft.modloader.LuaScript;
import fr.luacraft.modloader.LuacraftMod;
import fr.luacraft.modloader.scripts.ILuaScriptType;
import net.minecraft.client.gui.GuiScreen;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.reflections.Reflections;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Shared proxy
 * @author Zino
 */
@SuppressWarnings("deprecated")
public class SharedProxy
{
    public static final String SHARED_SCRIPT_PREFIX = "sh";

    /**
     * Lua stated
     */
    protected LuaState luaState;

    @Deprecated
    /**
     * @deprecated Prefix system was a way to differentiate client, servers an share scripts
     * but I still find this useless. So I mark this as Deprecated to flag that this system
     * doesn't work and may be removed or may be added in future versions
     */
    protected String scriptPrefix;

    /**
     * Proxy type/side
     */
    protected ProxyType type;

    /**
     * Current mod
     */
    private LuacraftMod currentMod;

    /**
     * Current exectued script
     */
    private LuaScript currentScript;

    /**
     * All found lua script types
     */
    private List<ILuaScriptType> scriptTypes;

    public SharedProxy()
    {
        NativeSupport.getInstance().setLoader(new LuaNativeLoader());

        this.type = ProxyType.SHARED;
        this.scriptPrefix = SHARED_SCRIPT_PREFIX;
        this.luaState = new LuaState();
        this.scriptTypes = new ArrayList<>();
    }

    /**
     * Called before modloader search mods
     */
    public void preModloader()
    {
        /** Find lua script types */
        Luacraft.getLogger().info("Finding lua script types...");
        Set<Class<? extends ILuaScriptType>> luaScriptTypes =
                new Reflections("fr.luacraft").getSubTypesOf(ILuaScriptType.class);
        if (!luaScriptTypes.isEmpty())
        {
            for (Class<? extends ILuaScriptType> clazz : luaScriptTypes)
            {
                try
                {
                    scriptTypes.add(clazz.newInstance());
                }
                catch (InstantiationException e)
                {
                    e.printStackTrace();
                }
                catch (IllegalAccessException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    public void setupLua()
    {
        /** Load lua libraries */
        luaState.openLib(LuaState.Library.BASE);
        luaState.openLib(LuaState.Library.PACKAGE);
        luaState.openLib(LuaState.Library.TABLE);
        //luaState.openLib(LuaState.Library.IO);
        luaState.openLib(LuaState.Library.OS);
        luaState.openLib(LuaState.Library.STRING);
        luaState.openLib(LuaState.Library.MATH);
        luaState.openLib(LuaState.Library.DEBUG);
        luaState.openLib(LuaState.Library.BIT32);
        luaState.openLib(LuaState.Library.JAVA);
        luaState.openLib(LuaState.Library.COROUTINE);

        /** Set _R to registry index */
        luaState.pushValue(LuaState.REGISTRYINDEX);
        luaState.setGlobal("_R");

        /** Load shared libraries annotated with {@link LuaLibrary} */
        Luacraft.getLogger().info("Registering shared libraries...");
        Luacraft.getLogger().info(registerLibraries(ProxyType.SHARED) + " shared libraries registered");

        /** Register meta types annotated with {@link LuaMetaClass} */
        Luacraft.getLogger().info("Registering meta classes...");
        Set<Class<?>> metaClasses =
                new Reflections("fr.luacraft.core.api").getTypesAnnotatedWith(LuaMetaClass.class);
        if (!metaClasses.isEmpty())
        {
            for (Class<?> clazz : metaClasses)
            {
                try
                {
                    MethodUtils.invokeStaticMethod(clazz, "initialize", luaState);
                }
                catch (NoSuchMethodException | IllegalAccessException |
                        InvocationTargetException e)
                {
                    e.printStackTrace();
                }
            }
        }
        Luacraft.getLogger().info(metaClasses.size() + " meta classes registered");

        /** Include internals */
        includeInternals();
    }

    protected int registerLibraries(ProxyType proxyType)
    {
        int registered = 0;

        Set<Class<?>> libClasses =
                new Reflections("fr.luacraft.core.api").getTypesAnnotatedWith(LuaLibrary.class);
        if (!libClasses.isEmpty())
        {
            for (Class<?> clazz : libClasses)
            {
                LuaLibrary ann = clazz.getAnnotation(LuaLibrary.class);

                if (ann.side() == proxyType)
                {
                    try
                    {
                        MethodUtils.invokeStaticMethod(clazz, "initialize", luaState);
                        registered++;
                    }
                    catch (NoSuchMethodException | IllegalAccessException |
                            InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return registered;
    }

    /**
     * Pre init event
     * @param event
     */
    public void preInit(FMLPreInitializationEvent event)
    {
        GameRegistry.registerTileEntity(LuacraftTileEntity.class, "luacraft_tile_entity");
        NetworkRegistry.INSTANCE.registerGuiHandler(Luacraft.getInstance(), new LuacraftGuiHandler());

        setupLua();

        /** Perform first execution */
        Luacraft.getInstance().getModLoader().executeAutorun();

        /**
         * Call preinit hooks on mods
         */
        Luacraft.getInstance().getModLoader().performPreInit();
    }

    /**
     * Init event
     * @param event
     */
    public void init(FMLInitializationEvent event)
    {
        LuacraftPacketHandler.registerMessages();

        /**
         * Call init hooks on mods
         */
        Luacraft.getInstance().getModLoader().performInit();
    }

    /**
     * Post init event
     * @param event
     */
    public void postInit(FMLPostInitializationEvent event)
    {
        /**
         * Call postinit hooks on mods
         */
        Luacraft.getInstance().getModLoader().performPostInit();
    }

    /**
     * Event trigerred when the server start
     * @param event
     */
    public void serverStarting(FMLServerStartingEvent event)
    {
        for(LuacraftMod mod : Luacraft.getInstance().getModLoader().getMods())
        {
            for (LuacraftCommand command : mod.getRegistryData().getServerCommands())
                event.registerServerCommand(command);
        }
    }

    /**
     * CLIENT-SIDE ONLY
     * Open a GUI
     * @param screen
     */
    public void openGUI(GuiScreen screen)
    {
        
    }

    /**
     * SERVER-SIDE ONLY
     * Server stopping
     */
    public void serverStopping(FMLServerStoppingEvent event)
    {

    }

    @SubscribeEvent
    public void onServerTick(TickEvent.ServerTickEvent event)
    {
        LuaTimerManager.tickTimers();
    }

    /**
     * Include internals
     */
    private void includeInternals()
    {
        Luacraft.getLogger().info("Loading and executing internals...");
        executeInternal("internal.lua");
        executeInternal("hook.lua");
    }

    private void executeInternal(String file)
    {
        InputStream in = getClass().getClassLoader()
                .getResourceAsStream("assets/luacraft/lua/" + file);
        executeScript(new LuaScript(new File("lua/" + file), file,
                        true, null),
                in);
    }

    /**
     * Execute a lua script
     * @param script
     */
    public void executeScript(LuaScript script)
    {
        try
        {
            FileInputStream in = new FileInputStream(script.getFile());
            executeScript(script, in);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Execute a lua script
     * @param script
     * @param in
     */
    public void executeScript(LuaScript script, InputStream in)
    {
        try
        {
            luaState.load(in, script.getFile().getPath(), "t");
            currentScript = script;
            luaState.call(0, 0);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Reset mod container to luacraft
     */
    public void resetModContainerToLuacraft()
    {
        currentMod = null;

        setModContainer(Luacraft.getInstance().getModContainer());
    }

    /**
     * Set the current mod container
     * @param modContainer
     */
    public void setModContainer(ModContainer modContainer)
    {
        try
        {
            LoadController modController = (LoadController) FieldUtils.readField(Loader.instance(), "modController", true);
            FieldUtils.writeField(modController, "activeContainer", modContainer, true);
        }
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Register a lua library
     * @param clazz
     */
    public void registerLuaLibrary(Class clazz)
    {
        LuaLibrary luaLibrary = (LuaLibrary) clazz.getAnnotation(LuaLibrary.class);
        if(luaLibrary != null)
        {
            if(type == luaLibrary.side())
            {
                try
                {
                    MethodUtils.invokeStaticMethod(clazz, "initialize", luaState);
                }
                catch (NoSuchMethodException e)
                {
                    e.printStackTrace();
                }
                catch (IllegalAccessException e)
                {
                    e.printStackTrace();
                }
                catch (InvocationTargetException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    public ILuaScriptType getLuaScriptTypeForScript(File script)
    {
        for(ILuaScriptType scriptType : scriptTypes)
        {
            if(scriptType.getDirectoryName() != null)
            {
                if (scriptType.getDirectoryName().equals(script.getParentFile().getName()))
                {
                    if(scriptType.getFilename() != null)
                    {
                        if(scriptType.getFilename().equals(script.getName()))
                            return scriptType;
                    }
                    else
                    {
                        return scriptType;
                    }
                }
            }
            else
            {
                if(scriptType.getFilename().equals(script.getName()))
                    return scriptType;
            }
        }

        return null;
    }

    /**
     * Get lua state
     * @return
     */
    public LuaState getLuaState()
    {
        return luaState;
    }


    /**
     * Get current side
     * @return
     */
    public Side getSide()
    {
        return FMLCommonHandler.instance().getEffectiveSide();
    }

    /**
     * Get current lua script
     * @return
     */
    public LuaScript getCurrentScript()
    {
        return currentScript;
    }
}
