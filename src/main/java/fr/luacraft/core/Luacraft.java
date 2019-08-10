package fr.luacraft.core;

import com.google.common.collect.ImmutableList;
import com.naef.jnlua.NativeSupport;
import cpw.mods.fml.common.*;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import fr.luacraft.core.proxy.SharedProxy;
import fr.luacraft.modloader.LuacraftModLoader;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.List;

/**
 * Base class for Luacraft
 * @author Zino
 */
@Mod(modid = Luacraft.MODID, version = Luacraft.VERSION)
public class Luacraft
{
    /** Mod ID */
    public static final String MODID = "luacraft";

    /** Mod version (replaced by gradle when jar is built) */
    public static final String VERSION = "{@version:luacraft}";

    /** List of Luacraft versions that doesn't mark mod as obsolete */
    public static final String[] COMPATIBLE_VERSIONS = new String[] { };

    /** Mod loader */
    public LuacraftModLoader modLoader;

    /** Mod logger */
    private static Logger logger;

    /** Mod proxy */
    @SidedProxy(clientSide="fr.luacraft.core.proxy.ClientProxy", serverSide="fr.luacraft.core.proxy.ServerProxy")
    public static SharedProxy proxy;

    /** Mod instance */
    @Mod.Instance
    private static Luacraft instance;

    private ModContainer modContainer;

    @EventHandler
    @SuppressWarnings("deprecation") /** ProgressManager is marked as Deprecated, so for not having warnings, we temporarily disable deprecation warnings*/
    public void preInit(FMLPreInitializationEvent event)
    {
        /** Create logger */
        modContainer = FMLCommonHandler.instance().findContainerFor(this);
        logger = LogManager.getLogger(modContainer.getName());

        modLoader = new LuacraftModLoader();
        /** Load all mods */

        if(!LuaNativeLoader.isInDevEnvironnement())
        {
            File luamodDir = new File(event.getModConfigurationDirectory().getParentFile(), "luamods");
            if (!luamodDir.exists())
                luamodDir.mkdirs();
            modLoader.addSearchDirectory(luamodDir.getPath());
        }
        else
        {
            // TODO: Setup a config file with support for supplying search dirs
            modLoader.addSearchDirectory("D:\\Projects\\LuaCraft\\luamods");
            modLoader.addSearchDirectory("C:\\Users\\Zino\\IdeaProjects\\LuaCraft\\luamods");
        }

        ProgressManager.ProgressBar bar = ProgressManager.push("LuaCraft", 1);
        bar.step("Searching mods");
        modLoader.loadMods();
        ProgressManager.pop(bar);

        /** Before executing mods, inject Luacraft infos to Forge brandings */
        injectBrandings();

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
    public LuacraftModLoader getModLoader()
    {
        return modLoader;
    }

    private void injectBrandings()
    {
        try
        {
            FMLCommonHandler.instance().computeBranding();
            List<String> brandingsImmutable = (List<String>) FieldUtils.readField(FMLCommonHandler.instance(),
                    "brandings", true);
            ImmutableList.Builder<String> builder = ImmutableList.<String>builder();
            for(String str : brandingsImmutable)
            {
                if(str.contains("load"))
                {
                    builder.add(String.format("Luacraft v%s", Luacraft.VERSION));
                }

                builder.add(str);
            }
            builder.add(String.format("%d lua mod%s loaded, %s obsolete%s",
                    modLoader.getMods().size(),
                    modLoader.getMods().size() > 1 ? "s" : "",
                    modLoader.getObsoleteMods().size(),
                    modLoader.getObsoleteMods().size() > 1 ? "s" : ""));
            List<String> brandings = builder.build();
            FieldUtils.writeField(FMLCommonHandler.instance(), "brandings", brandings,
                    true);
            FieldUtils.writeField(FMLCommonHandler.instance(), "brandingsNoMC",
                    brandings.subList(1, brandings.size()),
                    true);
        }
        catch(IllegalAccessException e)
        {
            e.printStackTrace();
        }
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

    /**
     * Get luacraft mod container
     * @return
     */
    public ModContainer getModContainer()
    {
        return modContainer;
    }
}
