package fr.luacraft.core.api.entity;

import fr.luacraft.core.api.hooks.LuaHookManager;
import fr.luacraft.core.api.nbt.LuaNBTTagCompound;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

/**
 * A tile entity compatible with Luacraft hooks
 * @author Zino
 */
public class LuacraftTileEntity extends TileEntity
{
    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);

        LuaHookManager.call(this, "ReadFromNBT", new LuaNBTTagCompound(nbt));
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);

        LuaHookManager.call(this, "WriteToNBT", new LuaNBTTagCompound(nbt));
    }
}
