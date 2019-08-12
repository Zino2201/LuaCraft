package fr.luacraft.core.api.reflection;

import com.naef.jnlua.util.LuaFunction;
import fr.luacraft.core.api.ILuaContainer;

import java.lang.reflect.Method;

/**
 * Container for {@link Method}
 *
 * @author Zino
 */
public class LuaMethod implements ILuaContainer
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
    public String GetTypeName()
    {
        return "Method";
    }

    @Override
    @LuaFunction
    public LuaJavaObject GetContainedObject()
    {
        return new LuaJavaObject(method);
    }
}
