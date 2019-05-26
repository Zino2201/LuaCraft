package fr.luacraft.core;

import net.minecraft.util.ResourceLocation;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Lua mod
 */
public class LuaMod
{
    public static final String MOD_INFO_FILENAME = "luamod.json";

    private String modid;
    private String name;
    private String version;
    private String luacraftversion;
    private String description;
    private File modDir;
    private List<File> scripts;

    public LuaMod(File modDir, File infoFile)
    {
        this.scripts = new ArrayList<File>();
        this.modid = modDir.getName();
        this.modDir = modDir;
        // TODO: Read JSON
        lookForScripts(modDir);
    }

    /**
     * Add all *.lua to the scripts list
     * @param dir
     */
    public void lookForScripts(File dir)
    {
        for(File file : dir.listFiles())
        {
            if(file.isDirectory())
                lookForScripts(file);
            else
            {
                if(file.getAbsolutePath().endsWith(".lua"))
                {
                    scripts.add(file);
                }
            }
        }
    }

    /**
     * Get a resource relative to mod resource directory
     * @param name
     * @return
     */
    public ResourceLocation getResource(String name)
    {
        return new ResourceLocation(modid, name);
    }

    /**
     * Get mod id
     * @return
     */
    public String getModID()
    {
        return modid;
    }

    /**
     * Get mod directory
     * @return
     */
    public File getModDirectory()
    {
        return modDir;
    }

    /**
     * Get all mod scripts
     * @return
     */
    public List<File> getScripts()
    {
        return scripts;
    }
}
