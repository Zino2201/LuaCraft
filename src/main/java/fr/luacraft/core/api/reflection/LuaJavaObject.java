package fr.luacraft.core.api.reflection;

import com.naef.jnlua.util.LuaFunction;
import fr.luacraft.core.api.ILuaContainer;

/**
 * Container for {@link Object}
 * @author Zino
 */
public class LuaJavaObject implements ILuaContainer
{
    private Object object;

    public LuaJavaObject(Object object)
    {
        this.object = object;
    }

    @LuaFunction
    public Object GetJavaObject()
    {
        return object;
    }

    @Override
    @LuaFunction
    public String GetTypeName()
    {
        return "JavaObject";
    }

    @Override
    @LuaFunction
    public LuaJavaObject GetContainedObject()
    {
        return this;
    }
}
