package fr.luacraft.modloader;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.naef.jnlua.LuaState;
import fr.luacraft.core.Luacraft;
import fr.luacraft.core.api.hooks.LuaHookManager;
import fr.luacraft.modloader.scripts.ILuaScriptType;
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

    /**
     * Mod metatable name
     */
    private String metaName;

    private LuacraftModLoader.PotentialMod potentialMod;

    public LuacraftMod(LuacraftModLoader.PotentialMod mod)
    {
        try
        {
            Gson gson = new Gson();
            JsonReader reader = new JsonReader(new FileReader(mod.getModInfo()));

            LuacraftModInfo modInfo = gson.fromJson(reader, LuacraftModInfo.class);
            setModInfo(modInfo);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        this.potentialMod = mod;
        this.scripts = new ArrayList<LuaScript>();
        this.modDir = mod.getRoot();
        this.registryData = new LuacraftModRegistryData();
        this.logger = LogManager.getLogger(getName());
        this.scriptDirectories = new ArrayList<File>();
        this.metaName = "LuaMod_" + getModId();

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
                    ILuaScriptType scriptType = Luacraft.getInstance().getProxy().getLuaScriptTypeForScript(file);

                    if(scriptType != null)
                    {
                        LuaScript script = new LuaScript(file, file.getName(), scriptType);
                        scripts.add(script);
                    }
                }
            }
        }
    }

    /**
     * On preinit hook
     */
    public void preInit()
    {
        LuaState l = Luacraft.getInstance().getProxy().getLuaState();

        /**
         * Execute main.lua
         */
        executeAllScriptsOfType(l, "Main");

        /** Now execute all block scripts */
        executeAllScriptsOfType(l, "Block");

        /** Items */
        executeAllScriptsOfType(l, "Item");
    }

    public void init()
    {
        LuaState l = Luacraft.getInstance().getProxy().getLuaState();

        List<LuaScript> mainScripts = getAllScriptsOfType("Main");
        for(LuaScript script : mainScripts)
        {
            LuaHookManager.callMetatable(l,
                    "OnInit",
                    metaName);
        }
    }

    public void postInit()
    {
        LuaState l = Luacraft.getInstance().getProxy().getLuaState();

        List<LuaScript> mainScripts = getAllScriptsOfType("Main");
        for(LuaScript script : mainScripts)
        {
            LuaHookManager.callMetatable(l,
                    "OnPostInit",
                    metaName);
        }
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
        return LuacraftFolderResourcePack.class;
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

    public String getMetaName()
    {
        return metaName;
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

    public void executeAllScriptsOfType(LuaState l, String type)
    {
        List<LuaScript> mainScripts = getAllScriptsOfType(type);
        for(LuaScript script : mainScripts)
        {
            script.execute(l);
        }
    }

    public List<LuaScript> getAllScriptsOfType(String type)
    {
        List<LuaScript> list = new ArrayList<>();

        for(LuaScript script : scripts)
            if(script.getType().getTypeName().equals(type))
                list.add(script);

        return list;
    }
}
