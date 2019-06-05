package fr.luacraft.modloader;

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
     * Get the contained java object, if any
     * @return
     */
    Object getObject();
}
