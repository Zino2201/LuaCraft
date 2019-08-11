package fr.luacraft.core.api.fluids;

import com.naef.jnlua.util.LuaFunction;
import fr.luacraft.core.api.ILuaObject;
import fr.luacraft.core.api.reflection.LuaJavaObject;
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
    @LuaFunction
    public String GetType()
    {
        return "Fluid";
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
        return new LuaJavaObject(fluid);
    }
}
