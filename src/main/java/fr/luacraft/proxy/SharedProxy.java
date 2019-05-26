package fr.luacraft.proxy;

import com.naef.jnlua.LuaState;
import com.naef.jnlua.NativeSupport;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import fr.luacraft.api.LuaBlock;
import fr.luacraft.api.LuaItem;
import fr.luacraft.api.libs.LuacraftLib;
import fr.luacraft.api.libs.MinecraftLib;
import fr.luacraft.command.CommandLuacraft;
import fr.luacraft.core.LuaMod;
import fr.luacraft.core.LuaNativeLoader;
import fr.luacraft.core.LuaObjectManager;
import fr.luacraft.core.Luacraft;
import net.minecraft.client.gui.GuiScreen;

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
        /** Setup lua classes */
        LuaObjectManager.registerClass("Block", LuaBlock.class);
        LuaObjectManager.registerClass("Item", LuaItem.class);

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
        LuaObjectManager.registerAll();
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
        for(LuaMod mod : Luacraft.getInstance().getModLoader().getMods())
        {
            for(File script : mod.getScripts())
            {
                if(script.getName().contains(scriptPrefix) || script.getName().contains(SHARED_SCRIPT_PREFIX))
                {
                    try
                    {
                        FileInputStream in = new FileInputStream(script);
                        luaState.load(in, script.getName(), "t");
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
    }
}
