package fr.luacraft.modloader;

import com.naef.jnlua.LuaState;
import fr.luacraft.modloader.scripts.ILuaScriptType;

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

    private ILuaScriptType type;

    public LuaScript(File file, String name, ILuaScriptType type)
    {
        this(file, name, false, type);
    }

    public LuaScript(File file, String name, boolean isInArchive, ILuaScriptType type)
    {
        this.file = file;
        this.name = name;
        this.type = type;
        this.isInArchive = isInArchive;
    }

    public void execute(LuaState l)
    {
        type.execute(l, this);
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

    public ILuaScriptType getType()
    {
        return type;
    }
}
