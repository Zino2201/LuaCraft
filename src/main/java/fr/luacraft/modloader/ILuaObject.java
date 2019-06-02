package fr.luacraft.modloader;

/**
 * Base for all lua objects
 * @author Zino
 */
public interface ILuaObject
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
