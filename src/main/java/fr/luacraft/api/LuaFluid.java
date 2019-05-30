package fr.luacraft.api;

import fr.luacraft.modloader.ILuaObject;
import net.minecraftforge.fluids.Fluid;

public class LuaFluid implements ILuaObject
{
    private Fluid fluid;

    public LuaFluid(Fluid fluid)
    {
        this.fluid = fluid;
    }

    @Override
    public String getType()
    {
        return "Fluid";
    }
}
