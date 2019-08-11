package fr.luacraft.core.api.nbt;

import com.naef.jnlua.util.LuaFunction;
import fr.luacraft.core.api.ILuaObject;
import fr.luacraft.core.api.reflection.LuaJavaObject;
import net.minecraft.nbt.NBTBase;

/**
 * Represents a NBTBase in Lua
 * @author Zino
 */
public class LuaNBTBase implements ILuaObject
{
    private NBTBase nbtBase;

    public LuaNBTBase(NBTBase nbtBase)
    {
        this.nbtBase = nbtBase;
    }

    @Override
    @LuaFunction
    public String GetType()
    {
        return "NBTBase";
    }

    @Override
    @LuaFunction
    public LuaJavaObject GetContainedObject()
    {
        return new LuaJavaObject(nbtBase);
    }

    @Override
    @LuaFunction
    public boolean IsContainer()
    {
        return true;
    }

    public NBTBase getNBTBase()
    {
        return nbtBase;
    }
}
