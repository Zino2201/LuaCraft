package fr.luacraft.core;

import cpw.mods.fml.common.DummyModContainer;
import cpw.mods.fml.common.ModMetadata;
import net.minecraft.util.ResourceLocation;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Lua mod
 */
public class LuacraftMod extends DummyModContainer
{
    public static final String MOD_INFO_FILENAME = "luamod.json";

    private File modDir;
    private List<File> scripts;

    public LuacraftMod(File modDir, File infoFile)
    {
        super(new ModMetadata());

        ModMetadata meta = getMetadata();
        meta.modId = modDir.getName();
        meta.name = meta.modId;

        this.scripts = new ArrayList<File>();
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
        return new ResourceLocation(getMetadata().modId, name);
    }

    @Override
    public Class<?> getCustomResourcePackClass()
    {
        try
        {
            return Class.forName("fr.luacraft.core.LuacraftFolderResourcePack", true, getClass().getClassLoader());
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public File getSource()
    {
        return modDir;
    }

    @Override
    public Object getMod()
    {
        return this;
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
