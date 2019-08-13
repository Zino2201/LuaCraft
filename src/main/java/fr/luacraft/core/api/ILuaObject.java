package fr.luacraft.core.api;

import com.naef.jnlua.util.LuaFunction;

import java.io.Serializable;

/**
 * ILuaObject represents a Object as seen by Lua
 *
 * If you want to create a {@link Object} container, use {@link ILuaContainer}
 * To check if a {@link ILuaObject} is a {@link ILuaContainer}, use luacraft.isContainer(obj)
 *
 * @author Zino
 */
public interface ILuaObject extends Serializable
{
    /**
     * Get the type string
     * @return
     */
    @LuaFunction
    String GetTypeName();
}
