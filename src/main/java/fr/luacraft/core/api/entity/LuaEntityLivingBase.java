package fr.luacraft.core.api.entity;

import fr.luacraft.core.api.ILuaObject;
import net.minecraft.entity.EntityLivingBase;

public class LuaEntityLivingBase implements ILuaObject
{
    private EntityLivingBase entity;

    public LuaEntityLivingBase(EntityLivingBase entity)
    {
        this.entity = entity;
    }

    @Override
    public String GetType()
    {
        return "EntityLivingBase";
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
