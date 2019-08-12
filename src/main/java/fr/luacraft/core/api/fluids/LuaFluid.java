package fr.luacraft.core.api.fluids;

import com.naef.jnlua.util.LuaFunction;
import fr.luacraft.core.api.ILuaContainer;
import fr.luacraft.core.api.reflection.LuaJavaObject;
import net.minecraftforge.fluids.Fluid;

/**
 * Represents a fluid in Lua
 * @author Zino
 */
public class LuaFluid implements ILuaContainer
{
    private Fluid fluid;

    public LuaFluid(Fluid fluid)
    {
        this.fluid = fluid;
    }

    @Override
    @LuaFunction
    public String GetTypeName()
    {
        return "Fluid";
    }

    @Override
    @LuaFunction
    public LuaJavaObject GetContainedObject()
    {
        return new LuaJavaObject(fluid);
    }
}
