package fr.luacraft.core.api.blocks;

import fr.luacraft.core.api.ILuaObject;
import net.minecraft.block.material.Material;

public class LuaMaterial implements ILuaObject
{
    private Material material;

    public LuaMaterial(Material material)
    {
        this.material = material;
    }

    @Override
    public String GetType()
    {
        return "Material";
    }

    @Override
    public boolean isContainer()
    {
        return true;
    }

    @Override
    public Object getObject()
    {
        return material;
    }
}
