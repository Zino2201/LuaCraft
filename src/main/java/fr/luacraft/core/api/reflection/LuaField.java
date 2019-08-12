package fr.luacraft.core.api.reflection;

import com.naef.jnlua.util.LuaFunction;
import fr.luacraft.core.api.ILuaContainer;

import java.lang.reflect.Field;

/**
 * Container for {@link Field}
 *
 * @author Zino
 */
public class LuaField implements ILuaContainer
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
    public String GetTypeName()
    {
        return "Field";
    }

    @Override
    @LuaFunction
    public LuaJavaObject GetContainedObject()
    {
        return new LuaJavaObject(field);
    }
}
