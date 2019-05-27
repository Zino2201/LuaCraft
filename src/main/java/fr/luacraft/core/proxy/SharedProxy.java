package fr.luacraft.core.proxy;

import com.naef.jnlua.LuaState;
import com.naef.jnlua.NativeSupport;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.LoadController;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import fr.luacraft.api.libs.HookLib;
import fr.luacraft.api.libs.LuacraftLib;
import fr.luacraft.api.libs.MinecraftLib;
import fr.luacraft.core.LuaNativeLoader;
import fr.luacraft.core.Luacraft;
import fr.luacraft.core.command.CommandLuacraft;
import fr.luacraft.modloader.LuacraftMod;
import net.minecraft.client.gui.GuiScreen;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

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

    public SharedProxy()
    {
        NativeSupport.getInstance().setLoader(new LuaNativeLoader());

        this.scriptPrefix = SHARED_SCRIPT_PREFIX;
        this.luaState = new LuaState();
    }

    /**
     * Pre init event
     * @param event
     */
    public void preInit(FMLPreInitializationEvent event)
    {
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
        ModContainer luacraftModContainer = FMLCommonHandler.instance().findContainerFor(Luacraft.getInstance());

        for(LuacraftMod mod : Luacraft.getInstance().getModLoader().getMods())
        {
            setCurrentMod(mod);

            for(File script : mod.getScripts())
            {
                if(script.getName().contains(scriptPrefix) || script.getName().contains(SHARED_SCRIPT_PREFIX))
                {
                    try
                    {
                        FileInputStream in = new FileInputStream(script);
                        luaState.load(in, script.getPath(), "t");
                        luaState.call(0, 0);
                        in.close();
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
            }
        }

        setModContainer(luacraftModContainer);
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
