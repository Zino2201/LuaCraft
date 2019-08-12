package fr.luacraft.core.api.reflection;

import com.naef.jnlua.util.LuaFunction;
import fr.luacraft.core.api.ILuaContainer;

import java.lang.reflect.Parameter;

/**
 * Container for {@link Parameter}
 *
 * @author Zino
 */
public class LuaParameter implements ILuaContainer
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
    @LuaFunction
    public String GetTypeName() {
        return null;
    }

    @Override
    @LuaFunction
    public LuaJavaObject GetContainedObject()
    {
        return new LuaJavaObject(parameter);
    }
}
