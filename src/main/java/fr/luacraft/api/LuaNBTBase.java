package fr.luacraft.api;

import fr.luacraft.modloader.ILuaObject;
import net.minecraft.nbt.NBTBase;

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

    public NBTBase getNBTBase()
    {
        return nbtBase;
    }
}
