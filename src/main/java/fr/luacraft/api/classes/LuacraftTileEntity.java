package fr.luacraft.api.classes;

import fr.luacraft.api.LuaNBTTagCompound;
import fr.luacraft.core.LuaHookManager;
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
