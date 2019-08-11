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
    public String GetType()
    {
        return "Field";
    }

    @Override
    public boolean IsContainer()
    {
        return true;
    }

    @Override
    public Object GetContainedObject()
    {
        return field;
    }
}
