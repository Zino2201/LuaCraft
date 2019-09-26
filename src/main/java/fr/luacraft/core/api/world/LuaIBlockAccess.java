package fr.luacraft.core.api.world;

import com.naef.jnlua.util.LuaFunction;
import fr.luacraft.core.api.ILuaContainer;
import fr.luacraft.core.api.reflection.LuaJavaObject;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class LuaIBlockAccess implements ILuaContainer
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
    public LuacraftWorld TryCastToWorld()
    {
        LuacraftWorld world = null;
        if(blockAccess instanceof World)
            world = new LuacraftWorld((World) blockAccess);

        return world;
    }

    @Override
    public String GetTypeName()
    {
        return "BlockAccess";
    }

    @Override
    public LuaJavaObject GetContainedObject()
    {
        return new LuaJavaObject(blockAccess);
    }
}
