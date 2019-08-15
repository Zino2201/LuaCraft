package fr.luacraft.modloader;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import cpw.mods.fml.common.ProgressManager;
import cpw.mods.fml.common.registry.GameRegistry;
import fr.luacraft.core.Luacraft;
import fr.luacraft.core.api.world.LuacraftWorldGen;
import org.apache.logging.log4j.Level;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Luacraft's Modloader
 *
 * Search luamods into specified search paths and load them
 */
// TODO: Implement obsolete system
public class LuacraftModLoader
{
    /**
     * Represents a potential mod
     */
    class PotentialMod
    {
        private File modInfo;
        private File root;
        private boolean isArchive;

        public PotentialMod(File modInfo, File root, boolean isArchive)
        {
            this.modInfo = modInfo;
            this.root = root;
            this.isArchive = isArchive;
        }

        public File getModInfo()
        {
            return modInfo;
        }

        public File getRoot()
        {
            return root;
        }

        public boolean isArchive()
        {
            return isArchive;
        }
    }

    /**
     * Minimal structure required for a luamod.json file
     */
    class RequiredModInfoStructure
    {
        private String id;
    }

    private List<String> searchPaths;
    private List<LuacraftMod> mods;
    private List<LuacraftMod> obsoleteMods;
    private LuacraftMod currentMod;

    public LuacraftModLoader()
    {
        this.searchPaths = new ArrayList<>();
        this.mods = new ArrayList<>();
        this.obsoleteMods = new ArrayList<>();
    }

    public void addSearchPath(String path)
    {
        searchPaths.add(path);
    }

    public void searchAndLoadMods()
    {
        List<PotentialMod> potentialMods = new ArrayList<PotentialMod>();
        Luacraft.getLogger().log(Level.INFO, "Searching mods");
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
                        potentialMods.add(new PotentialMod(infoFile, file, false));
                }
            }
        }
        loadPotentialMods(potentialMods);
    }

    private void loadPotentialMods(List<PotentialMod> potentialMods)
    {
        for(PotentialMod mod : potentialMods)
        {
            try
            {
                Gson gson = new Gson();
                JsonReader reader = new JsonReader(new FileReader(mod.getModInfo()));
                gson.fromJson(reader, RequiredModInfoStructure.class);
            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }

            LuacraftMod theMod = new LuacraftMod(mod);
            mods.add(theMod);
        }
        Luacraft.getLogger().info("Active mods: " + mods.size());
    }

    /**
     * Execute mods autorun scripts
     */
    public void executeAutorun()
    {
        Luacraft.getLogger().info("Performing first execution...");
        ProgressManager.ProgressBar bar = ProgressManager.push("LuaCraft", Luacraft.getInstance().getModLoader().getMods().size());

        for(LuacraftMod mod : Luacraft.getInstance().getModLoader().getMods())
        {
            bar.step("Executing " + mod.getName());

            setCurrentMod(mod);

            GameRegistry.registerWorldGenerator(new LuacraftWorldGen(mod), 2);

            for(LuaScript script : mod.getAllScriptsOfType(LuaScriptType.AUTORUN))
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

    public void performPreInit()
    {
        Luacraft.getLogger().info("Pre-initializing mods...");
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

    public void performInit()
    {

    }

    public void performPostInit()
    {

    }

    public void setCurrentMod(LuacraftMod mod)
    {
        this.currentMod = mod;
        Luacraft.getInstance().getProxy().setModContainer(mod);
    }

    public List<LuacraftMod> getMods()
    {
        return mods;
    }

    public List<LuacraftMod> getObsoleteMods()
    {
        return obsoleteMods;
    }

    public LuacraftMod getCurrentMod()
    {
        return currentMod;
    }
}
