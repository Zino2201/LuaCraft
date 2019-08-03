package fr.luacraft.core.api.entity;

import fr.luacraft.core.api.ILuaObject;
import net.minecraft.entity.Entity;

public class LuaEntity implements ILuaObject
{
    private Entity entity;

    public LuaEntity(Entity entity)
    {
        this.entity = entity;
    }

    @Override
    public String GetType()
    {
        return "Entity";
    }

    @Override
    public boolean isContainer()
    {
        return true;
    }

    @Override
    public Object getObject()
    {
        return entity;
    }
}
