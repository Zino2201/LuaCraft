package fr.luacraft.core.api.world;

import com.naef.jnlua.util.LuaFunction;
import fr.luacraft.core.api.ILuaObject;
import fr.luacraft.core.api.reflection.LuaJavaObject;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class LuaIBlockAccess implements ILuaObject
{
    private IBlockAccess blockAccess;

    public LuaIBlockAccess(IBlockAccess blockAccess)
    {
        this.blockAccess = blockAccess;
    }

    public IBlockAccess getBlockAccess()
    {
        return blockAccess;
    }

    /**
     * Try cast IBlockAccess to world
     * May return null
     * @return
     */
    @LuaFunction
    public LuaWorld TryCastToWorld()
    {
        LuaWorld world = null;
        if(blockAccess instanceof World)
            world = new LuaWorld((World) blockAccess);

        return world;
    }

    @Override
    public String GetType()
    {
        return "BlockAccess";
    }

    @Override
    public boolean IsContainer()
    {
        return true;
    }

    @Override
    public LuaJavaObject GetContainedObject()
    {
        return new LuaJavaObject(blockAccess);
    }
}
