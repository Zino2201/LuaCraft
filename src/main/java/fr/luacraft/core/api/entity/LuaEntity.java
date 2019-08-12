package fr.luacraft.core.api.entity;

import fr.luacraft.core.api.ILuaContainer;
import fr.luacraft.core.api.reflection.LuaJavaObject;
import net.minecraft.entity.Entity;

public class LuaEntity implements ILuaContainer
{
    private Entity entity;

    public LuaEntity(Entity entity)
    {
        this.entity = entity;
    }

    @Override
    public String GetTypeName()
    {
        return "Entity";
    }

    @Override
    public LuaJavaObject GetContainedObject()
    {
        return new LuaJavaObject(entity);
    }
}
