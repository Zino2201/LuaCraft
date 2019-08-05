package fr.luacraft.core.api.util;

import fr.luacraft.core.api.ILuaObject;
import net.minecraft.util.ChunkCoordinates;

public class LuaChunkCoordinates implements ILuaObject
{
    private ChunkCoordinates chunkCoordinates;

    public LuaChunkCoordinates(ChunkCoordinates chunkCoordinates)
    {
        this.chunkCoordinates = chunkCoordinates;
    }

    @Override
    public String GetType()
    {
        return "ChunkCoordinates";
    }

    @Override
    public boolean isContainer()
    {
        return true;
    }

    @Override
    public Object getObject()
    {
        return chunkCoordinates;
    }
}
