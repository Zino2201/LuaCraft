package fr.luacraft.core.api.fluids;

import fr.luacraft.core.api.ILuaObject;
import net.minecraftforge.fluids.Fluid;

/**
 * Represents a fluid in Lua
 * @author Zino
 */
public class LuaFluid implements ILuaObject
{
    private Fluid fluid;

    public LuaFluid(Fluid fluid)
    {
        this.fluid = fluid;
    }

    @Override
    public String GetType()
    {
        return "Fluid";
    }

    @Override
    public boolean IsContainer()
    {
        return true;
    }

    @Override
    public Object GetContainedObject()
    {
        return fluid;
    }
}
