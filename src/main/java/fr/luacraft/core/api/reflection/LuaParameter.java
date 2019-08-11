package fr.luacraft.core.api.reflection;

import com.naef.jnlua.util.LuaFunction;
import fr.luacraft.core.api.ILuaObject;

import java.lang.reflect.Parameter;

public class LuaParameter implements ILuaObject
{
    private Parameter parameter;

    public LuaParameter(Parameter parameter)
    {
        this.parameter = parameter;
    }

    @LuaFunction
    public String GetName()
    {
        return parameter.getName();
    }

    @LuaFunction
    public LuaJavaClass GetTypeClass()
    {
        return new LuaJavaClass(parameter.getType());
    }

    @Override
    public String GetType() {
        return null;
    }

    @Override
    public boolean IsContainer() {
        return false;
    }

    @Override
    public Object GetContainedObject() {
        return null;
    }
}
