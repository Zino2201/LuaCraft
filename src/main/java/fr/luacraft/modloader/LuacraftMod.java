package fr.luacraft.modloader;

import cpw.mods.fml.common.ModMetadata;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Lua mod
 */
public class LuacraftMod extends LuacraftModContainer implements ILuaContainerObject
{
    private Logger logger;

    public static final String MOD_INFO_FILENAME = "luamod.json";

    private File modDir;
    private List<File> scripts;
    private LuacraftModRegistryData registryData;

    public LuacraftMod(File modDir, File infoFile)
    {
        super(new ModMetadata());

        ModMetadata meta = getMetadata();
        meta.modId = modDir.getName();
        meta.name = meta.modId;

        this.scripts = new ArrayList<File>();
        this.modDir = modDir;
        this.registryData = new LuacraftModRegistryData();
        this.logger = LogManager.getLogger(getName());

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
            return Class.forName("fr.luacraft.modloader.LuacraftFolderResourcePack", true, getClass().getClassLoader());
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

    public LuacraftModRegistryData getRegistryData()
    {
        return registryData;
    }

    public Logger getLogger()
    {
        return logger;
    }

    /**
     * Get all mod scripts
     * @return
     */
    public List<File> getScripts()
    {
        return scripts;
    }

    @Override
    public ContainerObjectType getType()
    {
        return ContainerObjectType.JAVA_OBJECT;
    }
}
