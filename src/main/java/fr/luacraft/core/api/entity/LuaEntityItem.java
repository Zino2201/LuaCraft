package fr.luacraft.core.api.entity;

import com.naef.jnlua.util.LuaFunction;
import fr.luacraft.core.api.ILuaObject;
import fr.luacraft.core.api.reflection.LuaJavaObject;
import net.minecraft.entity.item.EntityItem;

public class LuaEntityItem implements ILuaObject
{
    private EntityItem entityItem;

    public LuaEntityItem(EntityItem entityItem)
    {
        this.entityItem = entityItem;
    }

    @Override
    @LuaFunction
    public String GetType()
    {
        return "EntityItem";
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
        return new LuaJavaObject(entityItem);
    }
}
