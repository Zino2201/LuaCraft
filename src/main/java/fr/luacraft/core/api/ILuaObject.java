package fr.luacraft.core.api;

import com.naef.jnlua.util.LuaFunction;
import fr.luacraft.core.api.reflection.LuaJavaObject;

import java.io.Serializable;

/**
 * ILuaObject is the main interface for all userdata objects
 * It can act as a JavaObject container.
 *
 * When it is set as a container, all hooks will reference the contained object
 * @author Zino
 */
public interface ILuaObject extends Serializable
{
    /**
     * Get the type string
     * @return
     */
    @LuaFunction
    String GetType();

    /**
     * Is a container
     * @return
     */
    @LuaFunction
    boolean IsContainer();

    /**
     * Get the contained java object, if any
     * @return
     */
    @LuaFunction
    LuaJavaObject GetContainedObject();
}
