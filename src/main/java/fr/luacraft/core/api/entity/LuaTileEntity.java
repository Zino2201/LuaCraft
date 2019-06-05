package fr.luacraft.core.api.entity;

import fr.luacraft.core.api.ILuaObject;
import net.minecraft.tileentity.TileEntity;

/**
 * Represents a TileEntity in Lua
 * @author Zino
 */
public class LuaTileEntity implements ILuaObject
{
    private TileEntity tileEntity;

    public LuaTileEntity(TileEntity tileEntity)
    {
        this.tileEntity = tileEntity;
    }

    @Override
    public String GetType()
    {
        return "TileEntity";
    }

    @Override
    public boolean isContainer()
    {
        return true;
    }

    @Override
    public Object getObject()
    {
        return tileEntity;
    }

    public TileEntity getTileEntity()
    {
        return tileEntity;
    }
}
