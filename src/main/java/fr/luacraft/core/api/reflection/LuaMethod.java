package fr.luacraft.core.api.reflection;

import com.naef.jnlua.util.LuaFunction;
import fr.luacraft.core.api.ILuaObject;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class LuaMethod implements ILuaObject
{
    private Method method;

    public LuaMethod(Method method)
    {
        this.method = method;
    }

    @LuaFunction
    public String GetName()
    {
        return method.getName();
    }

    @LuaFunction
    public String GetReturnTypeName()
    {
        return method.getReturnType().getName();
    }

    @LuaFunction
    public LuaParameter[] GetParameters()
    {
        LuaParameter[] ret = new LuaParameter[method.getParameters().length];
        for(int i = 0; i < method.getParameters().length; i++)
            ret[i] = new LuaParameter(method.getParameters()[i]);
        return ret;
    }

    @Override
    @LuaFunction
    public String GetType()
    {
        return "Method";
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
        return new LuaJavaObject(method);
    }
}
