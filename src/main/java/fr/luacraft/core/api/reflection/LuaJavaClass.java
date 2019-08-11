package fr.luacraft.core.api.reflection;

import com.naef.jnlua.util.LuaFunction;
import fr.luacraft.core.api.ILuaObject;

public class LuaJavaClass implements ILuaObject
{
    private Class clazz;

    public LuaJavaClass(Class clazz)
    {
        this.clazz = clazz;
    }

    @LuaFunction
    public String GetName()
    {
        return clazz.getName();
    }

    @Override
    @LuaFunction
    public String GetType()
    {
        return "JavaClass";
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
        return new LuaJavaObject(clazz);
    }
}
