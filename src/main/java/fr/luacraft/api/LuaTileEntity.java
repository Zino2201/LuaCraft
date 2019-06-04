package fr.luacraft.api;

import fr.luacraft.modloader.ILuaObject;
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
    public Object getObject()
    {
        return tileEntity;
    }

    public TileEntity getTileEntity()
    {
        return tileEntity;
    }
}
