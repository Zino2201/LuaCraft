package fr.luacraft.modloader;

/**
 * Java representation of luamod.json file
 * @author Zino
 */
public class LuacraftModInfo
{
    private String modId;
    private String name;
    private String version;
    private String description;

    public String getModId()
    {
        return modId;
    }

    public String getName()
    {
        return name;
    }

    public String getVersion()
    {
        return version;
    }

    public String getDescription()
    {
        return description;
    }
}
