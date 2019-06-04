package fr.luacraft.api;

import fr.luacraft.modloader.ILuaObject;
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
    public Object getObject()
    {
        return entityPlayer;
    }

    public EntityPlayer getEntityPlayer()
    {
        return entityPlayer;
    }
}
