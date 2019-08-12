package fr.luacraft.core.api.reflection;

import com.naef.jnlua.util.LuaFunction;
import fr.luacraft.core.api.ILuaContainer;

/**
 * Container for {@link Class}
 *
 * @author Zino
 */
public class LuaJavaClass implements ILuaContainer
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
    public String GetTypeName()
    {
        return "JavaClass";
    }

    @Override
    @LuaFunction
    public LuaJavaObject GetContainedObject()
    {
        return new LuaJavaObject(clazz);
    }
}
