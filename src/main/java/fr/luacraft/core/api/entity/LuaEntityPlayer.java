package fr.luacraft.core.api.entity;

import com.naef.jnlua.util.LuaFunction;
import fr.luacraft.core.api.ILuaContainer;
import fr.luacraft.core.api.reflection.LuaJavaObject;
import net.minecraft.entity.player.EntityPlayer;

public class LuaEntityPlayer implements ILuaContainer
{
    private EntityPlayer entityPlayer;

    public LuaEntityPlayer(EntityPlayer entityPlayer)
    {
        this.entityPlayer = entityPlayer;
    }

    @Override
    @LuaFunction
    public String GetTypeName()
    {
        return "EntityPlayer";
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
