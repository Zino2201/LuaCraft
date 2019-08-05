package fr.luacraft.core.api.entity;

import fr.luacraft.core.api.util.LuaClass;
import fr.luacraft.core.api.nbt.LuaNBTTagCompound;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

/**
 * A tile entity compatible with Luacraft hooks
 * @author Zino
 */
public class LuacraftTileEntity extends TileEntity
{
    private LuaClass tileEntityClass;

    public LuacraftTileEntity(LuaClass tileEntityClass)
    {
        this.tileEntityClass = tileEntityClass;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);

        tileEntityClass.CallFunction("ReadFromNBT", new LuaNBTTagCompound(nbt));
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);

        tileEntityClass.CallFunction("WriteToNBT", new LuaNBTTagCompound(nbt));
    }
}
