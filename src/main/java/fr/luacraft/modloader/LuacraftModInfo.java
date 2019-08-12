package fr.luacraft.modloader;

/**
 * Deserialized version of a luamod.json file
 * @author Zino
 */
public class LuacraftModInfo
{
    private String modid;
    private String name;
    private String version;
    private String luacraftversion;
    private String description;
    private String[] authors;
    private String website;
    private String logo;

    public LuacraftModInfo()
    {
        this.modid = "";
        this.name = "";
        this.version = "";
        this.luacraftversion = "";
        this.description = "";
        this.website = "";
        this.logo = "";
    }

    public String getModId()
    {
        return modid;
    }

    public String getName()
    {
        return name;
    }

    public String getVersion()
    {
        return version;
    }

    public String getLuacraftVersion()
    {
        return luacraftversion;
    }

    public String getDescription()
    {
        return description;
    }

    public String[] getAuthors()
    {
        return authors;
    }

    public String getWebsite()
    {
        return website;
    }

    public String getLogo()
    {
        return logo;
    }
}
