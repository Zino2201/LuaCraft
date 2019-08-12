package fr.luacraft.core.api.blocks;

import com.naef.jnlua.util.LuaFunction;
import fr.luacraft.core.api.ILuaContainer;
import fr.luacraft.core.api.reflection.LuaJavaObject;
import net.minecraft.block.material.Material;

public class LuaMaterial implements ILuaContainer
{
    private Material material;

    public LuaMaterial(Material material)
    {
        this.material = material;
    }

    @Override
    @LuaFunction
    public String GetTypeName()
    {
        return "Material";
    }

    @Override
    @LuaFunction
    public LuaJavaObject GetContainedObject()
    {
        return new LuaJavaObject(material);
    }
}
