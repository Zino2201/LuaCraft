package fr.luacraft.core.api.entity;

import fr.luacraft.core.api.ILuaObject;
import net.minecraft.entity.item.EntityItem;

public class LuaEntityItem implements ILuaObject
{
    private EntityItem entityItem;

    public LuaEntityItem(EntityItem entityItem)
    {
        this.entityItem = entityItem;
    }

    @Override
    public String GetType()
    {
        return "EntityItem";
    }

    @Override
    public boolean IsContainer()
    {
        return true;
    }

    @Override
    public Object GetContainedObject()
    {
        return entityItem;
    }
}
