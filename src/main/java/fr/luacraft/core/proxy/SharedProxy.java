package fr.luacraft.core.proxy;

import com.naef.jnlua.LuaState;
import com.naef.jnlua.NativeSupport;
import cpw.mods.fml.common.*;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import fr.luacraft.api.classes.LuacraftTileEntity;
import fr.luacraft.api.libs.HookLib;
import fr.luacraft.api.libs.LuacraftLib;
import fr.luacraft.api.libs.MinecraftLib;
import fr.luacraft.api.world.LuacraftWorldGen;
import fr.luacraft.core.LuaNativeLoader;
import fr.luacraft.core.Luacraft;
import fr.luacraft.core.command.CommandLuacraft;
import fr.luacraft.modloader.LuaGameRegistry;
import fr.luacraft.modloader.LuacraftMod;
import fr.luacraft.util.LuaUtil;
import net.minecraft.client.gui.GuiScreen;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Shared proxy
 */
public class SharedProxy
{
    public static final String SHARED_SCRIPT_PREFIX = "sh";

    protected LuaState luaState;
    protected String scriptPrefix;

    /**
     * Current mod
     */
    private LuacraftMod currentMod;

    private List<File> internals;

    public SharedProxy()
    {
        NativeSupport.getInstance().setLoader(new LuaNativeLoader());

        this.scriptPrefix = SHARED_SCRIPT_PREFIX;
        this.luaState = new LuaState();
        this.internals = new ArrayList<File>();
    }

    /**
     * Pre init event
     * @param event
     */
    public void preInit(FMLPreInitializationEvent event)
    {
        GameRegistry.registerTileEntity(LuacraftTileEntity.class, "luacraft_tile_entity");

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
            LuacraftLib.Initialize(luaState);
            MinecraftLib.Initialize(luaState);
            HookLib.Initialize(luaState);

            /** Include internals */
            includeInternals();

            /** Execute scripts */
            executeScripts();
        }
    }

    /**
     * Init event
     * @param event
     */
    public void init(FMLInitializationEvent event)
    {
        LuaGameRegistry.registerCraftAndSmelts();
    }

    /**
     * Post init event
     * @param event
     */
    public void postInit(FMLPostInitializationEvent event)
    {

    }

    /**
     * Event trigerred when the server start
     * @param event
     */
    public void serverStarting(FMLServerStartingEvent event)
    {
        event.registerServerCommand(new CommandLuacraft());
    }

    /**
     * CLIENT-SIDE ONLY
     * Open a GUI
     * @param screen
     */
    public void openGUI(GuiScreen screen)
    {
        
    }

    public void executeScripts()
    {
        ProgressManager.ProgressBar bar = ProgressManager.push("LuaCraft", Luacraft.getInstance().getModLoader().getMods().size());

        ModContainer luacraftModContainer = FMLCommonHandler.instance().findContainerFor(Luacraft.getInstance());

        for(LuacraftMod mod : Luacraft.getInstance().getModLoader().getMods())
        {
            bar.step("Executing " + mod.getMetadata().name);

            setCurrentMod(mod);

            GameRegistry.registerWorldGenerator(new LuacraftWorldGen(mod), 2);

            for(File script : mod.getScripts())
            {
                if(script.getName().contains(scriptPrefix) || script.getName().contains(SHARED_SCRIPT_PREFIX))
                {
                    LuaUtil.runFromFile(luaState, script);
                }
            }
        }

        setModContainer(luacraftModContainer);
        ProgressManager.pop(bar);
    }

    private void includeInternals()
    {
        // TODO: Read 'internal_paths.lua'
        InputStream in = getClass().getClassLoader()
                .getResourceAsStream("assets/luacraft/lua/internal.lua");
        LuaUtil.runFromFile(luaState, new File("lua/internal.lua"),  in);
    }

    private void setCurrentMod(LuacraftMod mod)
    {
        currentMod = mod;

        setModContainer(mod);
    }

    private void setModContainer(ModContainer modContainer)
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

    public LuaState getLuaState()
    {
        return luaState;
    }

    public LuacraftMod getCurrentMod()
    {
        return currentMod;
    }
}
