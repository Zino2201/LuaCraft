package fr.luacraft.core.api;

import com.naef.jnlua.util.LuaFunction;

import java.io.Serializable;

/**
 * Base for all lua objects
 * @author Zino
 */
public interface ILuaObject extends Serializable
{
    /**
     * Get the lua object type
     * @return
     */
    @LuaFunction
    String GetType();

    /**
     * Does the object contain a java object ?
     * @return
     */
    boolean isContainer();

    /**
     * Get the contained java object, if any
     * @return
     */
    Object getObject();
}
