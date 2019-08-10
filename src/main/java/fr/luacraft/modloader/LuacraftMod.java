package fr.luacraft.modloader;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import cpw.mods.fml.common.ModMetadata;
import fr.luacraft.core.api.hooks.LuaHookManager;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * A luacraft mod
 *
 * This class derive from LuacraftModContainer, so that the modloader wil be able to
 * temporarly change Forge current mod container to be able to give each mod its own domain
 * @author Zino
 */
public class LuacraftMod extends LuacraftModContainer
{
    private Logger logger;

    public static final String MOD_INFO_FILENAME = "luamod.json";

    /**
     * Mod directory
     */
    private File modDir;

    /**
     * Mod scripts list
     */
    private List<LuaScript> scripts;

    /**
     * Mod registry data
     */
    private LuacraftModRegistryData registryData;

    /**
     * List of all directories that contains scripts
     */
    private List<File> scriptDirectories;

    /**
     * Mark the mod as obsolete
     */
    private boolean isObsolete;

    public LuacraftMod(File modDir, File infoFile)
    {
        try
        {
            Gson gson = new Gson();
            JsonReader reader = new JsonReader(new FileReader(infoFile));

            LuacraftModInfo modInfo = gson.fromJson(reader, LuacraftModInfo.class);
            setModInfo(modInfo);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        this.scripts = new ArrayList<LuaScript>();
        this.modDir = modDir;
        this.registryData = new LuacraftModRegistryData();
        this.logger = LogManager.getLogger(getName());
        this.scriptDirectories = new ArrayList<File>();

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
            if(file.isDirectory() && !file.getName().equals("assets") && !file.getParentFile().getName().equals("assets"))
            {
                scriptDirectories.add(file);
                lookForScripts(file);
            }
            else
            {
                if(file.getAbsolutePath().endsWith(".lua"))
                {
                    scripts.add(new LuaScript(file, file.getName()));
                }
            }
        }
    }

    /**
     * On preinit hook
     */
    public void preInit()
    {
        LuaHookManager.call(this, "OnPreInit");
    }

    public void init()
    {
        LuaHookManager.call(this, "OnInit");
    }

    public void postInit()
    {
        LuaHookManager.call(this, "OnPostInit");
    }

    /**
     * Get a resource relative to mod resource directory
     * @param name
     * @return
     */
    public ResourceLocation getResource(String name)
    {
        return new ResourceLocation(getModId(), name);
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
    public List<LuaScript> getScripts()
    {
        return scripts;
    }

    public Collection<File> getScriptDirectories()
    {
        return scriptDirectories;
    }

    public void setObsolete(boolean isObsolete)
    {
        this.isObsolete = isObsolete;
    }

    public boolean isObsolete()
    {
        return isObsolete;
    }
}
