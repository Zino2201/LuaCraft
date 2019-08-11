package fr.luacraft.core.api.reflection;

import com.naef.jnlua.util.LuaFunction;
import fr.luacraft.core.api.ILuaObject;

/**
 * Container for a JavaObject
 * @author Zino
 */
public class LuaJavaObject implements ILuaObject
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
    public String GetType()
    {
        return "JavaObject";
    }

    @Override
    @LuaFunction
    public boolean IsContainer()
    {
        return true;
    }

    @Override
    @LuaFunction
    public LuaJavaObject GetContainedObject()
    {
        return this;
    }
}
