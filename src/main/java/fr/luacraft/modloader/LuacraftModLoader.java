package fr.luacraft.modloader;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.ProgressManager;
import cpw.mods.fml.common.registry.GameRegistry;
import fr.luacraft.core.Luacraft;
import fr.luacraft.core.api.world.LuacraftWorldGen;
import fr.luacraft.util.LuaUtil;
import org.apache.logging.log4j.Level;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Luacraft mod loader
 * LML works by searching through folders/archives and if it found a luamod.json,
 * mark the directory as a mod and create a LuacraftMod of it.
 *
 * Luacraft mod initialization is separated in 4 tasks:
 * - First, the proxy will execute the script one time to setup hooks and variables
 * - Moments after, the proxy will execute preinit hooks on all mods
 * - When init event is triggered by Forge, the proxy will execute init hooks on all mods
 * - And when the postinit event is triggered by Forge, the proxy will execute postinit hooks on all mods
 * At this point, the mod should be ready
 *
 * Luacraft mod loader give each mod a ModContainer and ResourcePack, allowing
 * mods to behave like Forge mods, with their own domain.
 *
 * Luacraft mod loader also have a small system that can mark obsolete mods.
 * TODO: If a obsolete mod is found, call a hook that return a boolean which should indicate if LML should disable this mod
 * @author Zino
 */
public class LuacraftModLoader
{
    /** List of mods search paths */
    private List<String> searchPaths;

    /** List of mods */
    private List<LuacraftMod> mods;

    /** List of obsolete mods */
    private List<LuacraftMod> obsoleteMods;

    /** Current mod */
    private LuacraftMod currentMod;

    public LuacraftModLoader()
    {
        this.searchPaths = new ArrayList<String>();
        this.mods = new ArrayList<LuacraftMod>();
        this.obsoleteMods = new ArrayList<LuacraftMod>();
        this.currentMod = null;
    }

    /**
     * Add a search directory
     * @param path
     */
    public void addSearchDirectory(String path)
    {
        searchPaths.add(path);
        Luacraft.getLogger().log(Level.INFO, "New search path added: " + path);
    }

    /**
     * Load all mods within searchPaths
     */
    public void loadMods()
    {
        Luacraft.getLogger().log(Level.INFO, "Loading mods");
        for(String path : searchPaths)
        {
            File root = new File(path);
            if(!root.exists())
                continue;

            for(File file : root.listFiles())
            {
                if(file.isDirectory())
                {
                    File infoFile = new File(file, LuacraftMod.MOD_INFO_FILENAME);
                    if(infoFile.exists() && !infoFile.isDirectory())
                        register(new LuacraftMod(file, infoFile));
                }
            }
        }
        Luacraft.getLogger().info("Active mods: " + mods.size());
    }

    /**
     * Perform mods first execution
     * This is very important, as its setup scripts and most importantly hooks
     * It's recommended that no block, item, creative tab, ... registry should be made in it
     * but instead in PreInit, Init and PostInit hooks
     */
    @SuppressWarnings("deprecated")
    public void performFirstExecution()
    {
        ProgressManager.ProgressBar bar = ProgressManager.push("LuaCraft", Luacraft.getInstance().getModLoader().getMods().size());

        for(LuacraftMod mod : Luacraft.getInstance().getModLoader().getMods())
        {
            bar.step("Executing " + mod.getName());

            setCurrentMod(mod);

            GameRegistry.registerWorldGenerator(new LuacraftWorldGen(mod), 2);

            for(LuaScript script : mod.getScripts())
            {
                // TODO: Remove prefix system if not needed
                //if(script.getName().contains(scriptPrefix) || script.getName().contains(SHARED_SCRIPT_PREFIX))
                //{
                Luacraft.getInstance().getProxy().executeScript(script);
                //}
            }
        }

        Luacraft.getInstance().getProxy().resetModContainerToLuacraft();
        ProgressManager.pop(bar);
    }

    /**
     * Call preinit hooks of all mods
     */
    public void peformPreInit()
    {
        ProgressManager.ProgressBar bar = ProgressManager.push("LuaCraft", Luacraft.getInstance().getModLoader().getMods().size());
        for(LuacraftMod mod : Luacraft.getInstance().getModLoader().getMods())
        {
            bar.step("Pre-init: " + mod.getName());
            setCurrentMod(mod);
            mod.preInit();
        }
        ProgressManager.pop(bar);
        Luacraft.getInstance().getProxy().resetModContainerToLuacraft();
    }

    /**
     * Call init hooks of all mods
     */
    public void peformInit()
    {
        ProgressManager.ProgressBar bar = ProgressManager.push("LuaCraft", Luacraft.getInstance().getModLoader().getMods().size());
        for(LuacraftMod mod : Luacraft.getInstance().getModLoader().getMods())
        {
            bar.step("Iinit: " + mod.getName());
            setCurrentMod(mod);
            mod.init();
        }
        ProgressManager.pop(bar);
        Luacraft.getInstance().getProxy().resetModContainerToLuacraft();
    }

    /**
     * Call post hooks of all mods
     */
    public void peformPostInit()
    {
        ProgressManager.ProgressBar bar = ProgressManager.push("LuaCraft", Luacraft.getInstance().getModLoader().getMods().size());
        for(LuacraftMod mod : Luacraft.getInstance().getModLoader().getMods())
        {
            bar.step("Post-init: " + mod.getName());
            setCurrentMod(mod);
            mod.postInit();
        }
        ProgressManager.pop(bar);
        Luacraft.getInstance().getProxy().resetModContainerToLuacraft();
    }

    public void setCurrentMod(LuacraftMod mod)
    {
        this.currentMod = mod;
        Luacraft.getInstance().getProxy().setModContainer(mod);
    }

    /**
     * Register a mod
     * @param mod
     */
    private void register(LuacraftMod mod)
    {
        mods.add(mod);
        if(considerModAsObsolete(mod))
        {
            mod.setObsolete(true);
            obsoleteMods.add(mod);
        }
        FMLCommonHandler.instance().addModToResourcePack(mod);
    }

    /**
     * Should we consider mod as obsolete
     * A mod is considered as obsolete if its supported Luacraft version isn't compatible with
     * user Luacraft version
     * @param mod
     * @return
     */
    private boolean considerModAsObsolete(LuacraftMod mod)
    {
        if(Luacraft.VERSION.equals(mod.getLuacraftVersion()))
            return false;

        for(String ver : Luacraft.COMPATIBLE_VERSIONS)
        {
            if(ver.equals(mod.getLuacraftVersion()))
                return false;
        }

        return true;
    }

    /**
     * Get a mod by ID
     * @param modid
     * @return
     */
    public LuacraftMod getModByID(String modid)
    {
        for(LuacraftMod mod : mods)
        {
            if (modid.equals(mod.getModId()))
                return mod;
        }

        return null;
    }

    public LuacraftMod getCurrentMod()
    {
        return currentMod;
    }

    /**
     * Get mods list
     * @return
     */
    public List<LuacraftMod> getMods()
    {
        return mods;
    }

    /**
     * Get obsolete mod list
     * @return
     */
    public List<LuacraftMod> getObsoleteMods()
    {
        return obsoleteMods;
    }
}
