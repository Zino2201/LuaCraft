package fr.luacraft.core.api.world;

import fr.luacraft.core.api.ILuaObject;
import net.minecraft.world.IBlockAccess;

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

    @Override
    public String GetType()
    {
        return "IBlockAccess";
    }

    @Override
    public boolean isContainer()
    {
        return true;
    }

    @Override
    public Object getObject()
    {
        return blockAccess;
    }
}
