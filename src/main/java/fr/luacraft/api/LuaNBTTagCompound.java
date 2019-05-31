package fr.luacraft.api;

import fr.luacraft.modloader.ILuaObject;
import net.minecraft.nbt.NBTTagCompound;

public class LuaNBTTagCompound implements ILuaObject
{
    private NBTTagCompound nbtTagCompound;

    public LuaNBTTagCompound(NBTTagCompound nbtTagCompound)
    {
        this.nbtTagCompound = nbtTagCompound;
    }

    public String GetString(String target)
    {
        return nbtTagCompound.getString(target);
    }

    public void SetBoolean(String target, boolean value)
    {
        nbtTagCompound.setBoolean(target, value);
    }

    public void SetByte(String target, byte value)
    {
        nbtTagCompound.setByte(target, value);
    }

    public void SetByteArray(String target, byte[] value)
    {
        nbtTagCompound.setByteArray(target, value);
    }

    public void SetDouble(String target, double value)
    {
        nbtTagCompound.setDouble(target, value);
    }

    public void SetFloat(String target, float value)
    {
        nbtTagCompound.setFloat(target, value);
    }

    public void SetInteger(String target, int value)
    {
        nbtTagCompound.setInteger(target, value);
    }

    public void SetIntArray(String target, int[] value)
    {
        nbtTagCompound.setIntArray(target, value);
    }

    public void SetLong(String target, long value)
    {
        nbtTagCompound.setLong(target, value);
    }

    public void SetShort(String target, short value)
    {
        nbtTagCompound.setShort(target, value);
    }

    public void SetString(String target, String value)
    {
        nbtTagCompound.setString(target, value);
    }

    public void SetTag(String target, LuaNBTBase value)
    {
        nbtTagCompound.setTag(target, value.getNBTBase());
    }

    @Override
    public String GetType()
    {
        return "NBTTagCompound";
    }

    @Override
    public Object getObject()
    {
        return nbtTagCompound;
    }
}
