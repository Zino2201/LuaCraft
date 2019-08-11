package fr.luacraft.core.api.entity;

import com.naef.jnlua.util.LuaFunction;
import fr.luacraft.core.api.ILuaObject;
import fr.luacraft.core.api.reflection.LuaJavaObject;
import net.minecraft.entity.player.EntityPlayer;

public class LuaEntityPlayer implements ILuaObject
{
    private EntityPlayer entityPlayer;

    public LuaEntityPlayer(EntityPlayer entityPlayer)
    {
        this.entityPlayer = entityPlayer;
    }

    @Override
    @LuaFunction
    public String GetType()
    {
        return "EntityPlayer";
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
        return new LuaJavaObject(entityPlayer);
    }

    public EntityPlayer getEntityPlayer()
    {
        return entityPlayer;
    }
}
