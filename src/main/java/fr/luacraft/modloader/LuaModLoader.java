package fr.luacraft.modloader;

import cpw.mods.fml.common.FMLCommonHandler;
import fr.luacraft.core.Luacraft;
import org.apache.logging.log4j.Level;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Luacraft mod loader
 * Find and load mods
 * @author Zino
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
    public List<LuacraftMod> mods;

    public LuaModLoader()
    {
        this.searchPaths = new ArrayList<String>();
        this.mods = new ArrayList<LuacraftMod>();
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
     * Register a mod
     * @param mod
     */
    private void register(LuacraftMod mod)
    {
        mods.add(mod);
        FMLCommonHandler.instance().addModToResourcePack(mod);
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

    /**
     * Get mods list
     * @return
     */
    public List<LuacraftMod> getMods()
    {
        return mods;
    }
}
