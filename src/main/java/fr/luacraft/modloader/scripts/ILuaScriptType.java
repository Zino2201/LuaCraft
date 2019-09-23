package fr.luacraft.modloader.scripts;

import com.naef.jnlua.LuaState;
import fr.luacraft.modloader.LuaScript;

/**
 * Represents a lua type script
 * All class that implements this interface will automatly be discovered by Luacraft
 */
public interface ILuaScriptType
{
    /**
     * Type name
     * @return
     */
    String getTypeName();

    /**
     * Name of directory where this type scripts should be stored
     * @return
     */
    String getDirectoryName();

    /**
     * Get parent type if any
     * @return
     */
    String getParentType();

    /**
     * Required filename
     */
    String getFilename();

    /**
     * On a script of this type is being exectued
     * @param l
     * @param script
     */
    void execute(LuaState l, LuaScript script);
}
