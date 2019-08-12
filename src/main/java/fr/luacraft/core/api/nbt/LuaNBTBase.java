package fr.luacraft.core.api.nbt;

import com.naef.jnlua.util.LuaFunction;
import fr.luacraft.core.api.ILuaContainer;
import fr.luacraft.core.api.reflection.LuaJavaObject;
import net.minecraft.nbt.NBTBase;

/**
 * Represents a NBTBase in Lua
 * @author Zino
 */
public class LuaNBTBase implements ILuaContainer
{
    private NBTBase nbtBase;

    public LuaNBTBase(NBTBase nbtBase)
    {
        this.nbtBase = nbtBase;
    }

    @Override
    @LuaFunction
    public String GetTypeName()
    {
        return "NBTBase";
    }

    @Override
    @LuaFunction
    public LuaJavaObject GetContainedObject()
    {
        return new LuaJavaObject(nbtBase);
    }

    public NBTBase getNBTBase()
    {
        return nbtBase;
    }
}
