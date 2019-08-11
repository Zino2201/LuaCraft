package fr.luacraft.core.api.entity;

import fr.luacraft.core.api.ILuaObject;
import fr.luacraft.core.api.reflection.LuaJavaObject;
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
    public boolean IsContainer()
    {
        return true;
    }

    @Override
    public LuaJavaObject GetContainedObject()
    {
        return new LuaJavaObject(entity);
    }
}
