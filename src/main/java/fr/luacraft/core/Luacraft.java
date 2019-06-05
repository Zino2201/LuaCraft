package fr.luacraft.core;

import cpw.mods.fml.common.*;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import fr.luacraft.core.proxy.SharedProxy;
import fr.luacraft.modloader.LuaModLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

/**
 * Base class for Luacraft
 * @author Zino
 */
@Mod(modid = Luacraft.MODID, version = Luacraft.VERSION, useMetadata = true)
public class Luacraft
{
    /** Mod ID */
    public static final String MODID = "luacraft";

    /** Mod version */
    public static final String VERSION = "{@version:luacraft}";

    /** Mod loader */
    public static LuaModLoader modLoader;

    /** Mod logger */
    private static Logger logger;

    /** Mod proxy */
    @SidedProxy(clientSide="fr.luacraft.core.proxy.ClientProxy", serverSide="fr.luacraft.core.proxy.ServerProxy")
    public static SharedProxy proxy;

    /** Mod instance */
    @Mod.Instance
    private static Luacraft instance;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        /** Create logger */
        ModContainer modContainer = FMLCommonHandler.instance().findContainerFor(this);
        logger = LogManager.getLogger(modContainer.getName());

        /** Load all mods */
        modLoader = new LuaModLoader();

        if(!LuaNativeLoader.isInDevEnvironnement())
        {
            File luamodDir = new File(event.getModConfigurationDirectory().getParentFile(), "luamods");
            if (!luamodDir.exists())
                luamodDir.mkdirs();
            modLoader.addSearchDirectory(luamodDir.getPath());
        }
        else
        {
            modLoader.addSearchDirectory("D:\\Projects\\LuaCraft\\luamods");
        }

        ProgressManager.ProgressBar bar = ProgressManager.push("LuaCraft", 1);
        bar.step("Searching mods");
        modLoader.loadMods();
        ProgressManager.pop(bar);

        proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        ProgressManager.ProgressBar bar = ProgressManager.push("LuaCraft", 1);
        bar.step("Registering crafts and smelts");
        proxy.init(event);
        ProgressManager.pop(bar);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        proxy.postInit(event);
    }

    @EventHandler
    public void serverStarting(FMLServerStartingEvent event)
    {
        proxy.serverStarting(event);
    }

    /**
     * Get lua mod loader
     * @return
     */
    public LuaModLoader getModLoader()
    {
        return modLoader;
    }

    /**
     * Get mod instance
     * @return
     */
    public static Luacraft getInstance()
    {
        return instance;
    }

    /**
     * Get logger
     * @return
     */
    public static Logger getLogger()
    {
        return logger;
    }

    /**
     * Get proxy
     * @return
     */
    public SharedProxy getProxy()
    {
        return proxy;
    }
}
