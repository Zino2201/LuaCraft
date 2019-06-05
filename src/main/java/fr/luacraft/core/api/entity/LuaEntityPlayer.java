package fr.luacraft.core.api.entity;

import fr.luacraft.core.api.ILuaObject;
import net.minecraft.entity.player.EntityPlayer;

public class LuaEntityPlayer implements ILuaObject
{
    private EntityPlayer entityPlayer;

    public LuaEntityPlayer(EntityPlayer entityPlayer)
    {
        this.entityPlayer = entityPlayer;
    }

    @Override
    public String GetType()
    {
        return "EntityPlayer";
    }

    @Override
    public boolean isContainer()
    {
        return true;
    }

    @Override
    public Object getObject()
    {
        return entityPlayer;
    }

    public EntityPlayer getEntityPlayer()
    {
        return entityPlayer;
    }
}
