package fr.luacraft.core.api.entity;

import com.naef.jnlua.util.LuaFunction;
import fr.luacraft.core.api.ILuaContainer;
import fr.luacraft.core.api.reflection.LuaJavaObject;
import net.minecraft.entity.item.EntityItem;

public class LuaEntityItem implements ILuaContainer
{
    private EntityItem entityItem;

    public LuaEntityItem(EntityItem entityItem)
    {
        this.entityItem = entityItem;
    }

    @Override
    @LuaFunction
    public String GetTypeName()
    {
        return "EntityItem";
    }

    @Override
    @LuaFunction
    public LuaJavaObject GetContainedObject()
    {
        return new LuaJavaObject(entityItem);
    }
}
