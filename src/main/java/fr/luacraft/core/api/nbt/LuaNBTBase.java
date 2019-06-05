package fr.luacraft.core.api.nbt;

import fr.luacraft.core.api.ILuaObject;
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
    public String GetType()
    {
        return "NBTBase";
    }

    @Override
    public Object getObject()
    {
        return nbtBase;
    }

    @Override
    public boolean isContainer()
    {
        return true;
    }

    public NBTBase getNBTBase()
    {
        return nbtBase;
    }
}
