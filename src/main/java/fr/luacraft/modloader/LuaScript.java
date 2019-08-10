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

    public LuaScript(File file, String name)
    {
        this.file = file;
        this.name = name;
    }

    public LuaScript(File file, String name, boolean isInArchive)
    {
        this.file = file;
        this.name = name;
        this.isInArchive = isInArchive;
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
}
