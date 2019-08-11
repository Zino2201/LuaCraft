package fr.luacraft.core.api.reflection;

import com.naef.jnlua.util.LuaFunction;
import fr.luacraft.core.api.ILuaObject;

import java.lang.reflect.Field;

public class LuaField implements ILuaObject
{
    private Field field;

    public LuaField(Field field)
    {
        this.field = field;
    }

    @LuaFunction
    public String GetName()
    {
        return field.getName();
    }

    @Override
    @LuaFunction
    public String GetType()
    {
        return "Field";
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
        return new LuaJavaObject(field);
    }
}
