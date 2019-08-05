package fr.luacraft.core.api;

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
