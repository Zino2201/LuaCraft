package fr.luacraft.core;

import org.apache.logging.log4j.Level;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Luacraft mod loader
 * Load all mods in searchPaths
 */
public class LuaModLoader
{
    /**
     * Search paths to search mods
     */
    public List<String> searchPaths;

    /**
     * Mods
     */
    public List<LuaMod> mods;

    public LuaModLoader()
    {
        this.searchPaths = new ArrayList<String>();
        this.mods = new ArrayList<LuaMod>();
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
            for(File file : root.listFiles())
            {
                if(file.isDirectory())
                {
                    File infoFile = new File(file, LuaMod.MOD_INFO_FILENAME);
                    if(infoFile.exists() && !infoFile.isDirectory())
                        register(new LuaMod(file, infoFile));
                }
            }
        }
        Luacraft.getLogger().log(Level.INFO, "Active mods: " + mods.size());
    }

    /**
     * Register a mod
     * @param mod
     */
    public void register(LuaMod mod)
    {
        mods.add(mod);
    }

    /**
     * Get a mod by ID
     * @param modid
     * @return
     */
    public LuaMod getModByID(String modid)
    {
        for(LuaMod mod : mods)
            if(modid == mod.getModID())
                return mod;
        Luacraft.getLogger().log(Level.ERROR, "No mods are using modid " + modid);
        return null;
    }

    /**
     * Get mods list
     * @return
     */
    public List<LuaMod> getMods()
    {
        return mods;
    }
}
