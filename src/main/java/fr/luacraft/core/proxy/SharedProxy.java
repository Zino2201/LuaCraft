package fr.luacraft.core.proxy;

import com.naef.jnlua.LuaState;
import com.naef.jnlua.NativeSupport;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.*;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import fr.luacraft.core.LuaNativeLoader;
import fr.luacraft.core.Luacraft;
import fr.luacraft.core.api.command.LuacraftCommand;
import fr.luacraft.core.api.entity.LuacraftTileEntity;
import fr.luacraft.core.api.libs.*;
import fr.luacraft.core.api.network.LuacraftPacketHandler;
import fr.luacraft.core.api.registry.LuaGameRegistry;
import fr.luacraft.core.api.world.LuacraftWorldGen;
import fr.luacraft.core.gui.LuacraftGuiHandler;
import fr.luacraft.modloader.LuaScript;
import fr.luacraft.modloader.LuacraftMod;
import fr.luacraft.util.LuaUtil;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.client.MinecraftForgeClient;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.commons.lang3.reflect.MethodUtils;

import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

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

    public SharedProxy()
    {
        NativeSupport.getInstance().setLoader(new LuaNativeLoader());

        this.type = ProxyType.SHARED;
        this.scriptPrefix = SHARED_SCRIPT_PREFIX;
        this.luaState = new LuaState();
    }

    /**
     * Pre init event
     * @param event
     */
    public void preInit(FMLPreInitializationEvent event)
    {
        GameRegistry.registerTileEntity(LuacraftTileEntity.class, "luacraft_tile_entity");
        NetworkRegistry.INSTANCE.registerGuiHandler(Luacraft.getInstance(), new LuacraftGuiHandler());

        synchronized (luaState)
        {
            /** Load lua libraries */
            luaState.openLib(LuaState.Library.BASE);
            luaState.openLib(LuaState.Library.PACKAGE);
            luaState.openLib(LuaState.Library.TABLE);
            luaState.openLib(LuaState.Library.IO);
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

            /** Load shared libraries */
            LuacraftLib.initialize(luaState);
            MinecraftLib.initialize(luaState);
            HookLib.initialize(luaState);
            NetLib.initialize(luaState);
            ReflLib.initialize(luaState);

            /** Include internals */
            includeInternals();

            /** Perform first execution */
            Luacraft.getInstance().getModLoader().performFirstExecution();

            /**
             * Call preinit hooks on mods
             */
            Luacraft.getInstance().getModLoader().peformPreInit();
        }
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
        Luacraft.getInstance().getModLoader().peformInit();
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
        Luacraft.getInstance().getModLoader().peformPostInit();
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

    /**
     * Include internals
     */
    private void includeInternals()
    {
        Luacraft.getLogger().info("Loading and executing internals...");
        InputStream in = getClass().getClassLoader()
                .getResourceAsStream("assets/luacraft/lua/internal.lua");
        executeScript(new LuaScript(new File("lua/internal.lua"), "internal.lua", true),
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
