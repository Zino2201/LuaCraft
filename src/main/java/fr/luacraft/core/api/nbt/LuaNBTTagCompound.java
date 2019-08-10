package fr.luacraft.core.api.nbt;

import com.naef.jnlua.util.LuaFunction;
import fr.luacraft.core.api.ILuaObject;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Represents a NBTTagCompound in Lua
 * @author Zino
 */
public class LuaNBTTagCompound implements ILuaObject
{
    private NBTTagCompound nbtTagCompound;

    public LuaNBTTagCompound(NBTTagCompound nbtTagCompound)
    {
        this.nbtTagCompound = nbtTagCompound;
    }

    @LuaFunction
    public String GetString(String target)
    {
        return nbtTagCompound.getString(target);
    }

    @LuaFunction
    public void SetBoolean(String target, boolean value)
    {
        nbtTagCompound.setBoolean(target, value);
    }

    @LuaFunction
    public void SetByte(String target, byte value)
    {
        nbtTagCompound.setByte(target, value);
    }

    @LuaFunction
    public void SetByteArray(String target, byte[] value)
    {
        nbtTagCompound.setByteArray(target, value);
    }

    @LuaFunction
    public void SetDouble(String target, double value)
    {
        nbtTagCompound.setDouble(target, value);
    }

    @LuaFunction
    public void SetFloat(String target, float value)
    {
        nbtTagCompound.setFloat(target, value);
    }

    @LuaFunction
    public void SetInteger(String target, int value)
    {
        nbtTagCompound.setInteger(target, value);
    }

    @LuaFunction
    public void SetIntArray(String target, int[] value)
    {
        nbtTagCompound.setIntArray(target, value);
    }

    @LuaFunction
    public void SetLong(String target, long value)
    {
        nbtTagCompound.setLong(target, value);
    }

    @LuaFunction
    public void SetShort(String target, short value)
    {
        nbtTagCompound.setShort(target, value);
    }

    @LuaFunction
    public void SetString(String target, String value)
    {
        nbtTagCompound.setString(target, value);
    }

    @LuaFunction
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
    public boolean IsContainer()
    {
        return true;
    }

    @Override
    public Object GetContainedObject()
    {
        return nbtTagCompound;
    }
}
