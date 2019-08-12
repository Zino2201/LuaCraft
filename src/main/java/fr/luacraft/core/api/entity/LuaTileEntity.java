package fr.luacraft.core.api.entity;

import fr.luacraft.core.api.ILuaContainer;
import fr.luacraft.core.api.reflection.LuaJavaObject;
import net.minecraft.tileentity.TileEntity;

/**
 * Represents a TileEntity in Lua
 * @author Zino
 */
public class LuaTileEntity implements ILuaContainer
{
    private TileEntity tileEntity;

    public LuaTileEntity(TileEntity tileEntity)
    {
        this.tileEntity = tileEntity;
    }

    @Override
    public String GetTypeName()
    {
        return "TileEntity";
    }

    @Override
    public LuaJavaObject GetContainedObject()
    {
        return new LuaJavaObject(tileEntity);
    }

    public TileEntity getTileEntity()
    {
        return tileEntity;
    }
}
