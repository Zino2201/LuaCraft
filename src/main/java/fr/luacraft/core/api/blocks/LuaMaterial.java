package fr.luacraft.core.api.blocks;

import com.naef.jnlua.util.LuaFunction;
import fr.luacraft.core.api.ILuaObject;
import fr.luacraft.core.api.reflection.LuaJavaObject;
import net.minecraft.block.material.Material;

public class LuaMaterial implements ILuaObject
{
    private Material material;

    public LuaMaterial(Material material)
    {
        this.material = material;
    }

    @Override
    @LuaFunction
    public String GetType()
    {
        return "Material";
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
        return new LuaJavaObject(material);
    }
}
