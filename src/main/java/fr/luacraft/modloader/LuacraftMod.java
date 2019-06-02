package fr.luacraft.modloader;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import cpw.mods.fml.common.ModMetadata;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Lua mod
 * @author Zino
 */
public class LuacraftMod extends LuacraftModContainer
{
    private Logger logger;

    public static final String MOD_INFO_FILENAME = "luamod.json";

    private File modDir;
    private List<File> scripts;
    private LuacraftModRegistryData registryData;

    public LuacraftMod(File modDir, File infoFile)
    {
        super(new ModMetadata());

        try
        {
            Gson gson = new Gson();
            JsonReader reader = new JsonReader(new FileReader(infoFile));

            ModMetadata meta = getMetadata();
            LuacraftModInfo modInfo = gson.fromJson(reader, LuacraftModInfo.class);
            meta.modId = modInfo.getModId() == null || modInfo.getModId().isEmpty() == true ? modDir.getName() : modInfo.getModId();
            meta.name = modInfo.getName() == null || modInfo.getName().isEmpty() == true ? meta.modId : modInfo.getName();
            meta.version = modInfo.getVersion();
            meta.description = modInfo.getDescription();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        this.scripts = new ArrayList<File>();
        this.modDir = modDir;
        this.registryData = new LuacraftModRegistryData();
        this.logger = LogManager.getLogger(getName());

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
}
