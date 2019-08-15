package fr.luacraft.modloader;

import java.io.File;

/**
 * Contains infos about a lua script
 * @author Zino
 */
public class LuaScript
{
    /**
     * File
     */
    private File file;

    /**
     * Script name
     */
    private String name;

    /**
     * Is script file contained in a archive ?
     */
    private boolean isInArchive;

    private LuaScriptType type;

    public LuaScript(File file, String name, LuaScriptType type)
    {
        this.file = file;
        this.name = name;
        this.type = type;
    }

    public LuaScript(File file, String name, boolean isInArchive, LuaScriptType type)
    {
        this.file = file;
        this.name = name;
        this.isInArchive = isInArchive;
        this.type = type;
    }

    public File getFile()
    {
        return file;
    }

    public String getName()
    {
        return name;
    }

    public boolean isInArchive()
    {
        return isInArchive;
    }

    public LuaScriptType getType()
    {
        return type;
    }
}
