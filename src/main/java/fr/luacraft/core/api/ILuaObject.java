package fr.luacraft.core.api;

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
