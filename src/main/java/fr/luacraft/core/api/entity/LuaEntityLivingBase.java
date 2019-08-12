package fr.luacraft.core.api.entity;

import com.naef.jnlua.util.LuaFunction;
import fr.luacraft.core.api.ILuaContainer;
import fr.luacraft.core.api.reflection.LuaJavaObject;
import net.minecraft.entity.EntityLivingBase;

public class LuaEntityLivingBase implements ILuaContainer
{
    private EntityLivingBase entity;

    public LuaEntityLivingBase(EntityLivingBase entity)
    {
        this.entity = entity;
    }

    @Override
    @LuaFunction
    public String GetTypeName()
    {
        return "EntityLivingBase";
    }

    @Override
    @LuaFunction
    public LuaJavaObject GetContainedObject()
    {
        return new LuaJavaObject(entity);
    }
}
