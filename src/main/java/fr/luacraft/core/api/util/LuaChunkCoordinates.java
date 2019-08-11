package fr.luacraft.core.api.util;

import com.naef.jnlua.util.LuaFunction;
import fr.luacraft.core.api.ILuaObject;
import fr.luacraft.core.api.reflection.LuaJavaObject;
import net.minecraft.util.ChunkCoordinates;

public class LuaChunkCoordinates implements ILuaObject
{
    private ChunkCoordinates chunkCoordinates;

    public LuaChunkCoordinates(ChunkCoordinates chunkCoordinates)
    {
        this.chunkCoordinates = chunkCoordinates;
    }

    @Override
    @LuaFunction
    public String GetType()
    {
        return "ChunkCoordinates";
    }

    @Override
    @LuaFunction
    public boolean IsContainer()
    {
        return true;
    }

    @Override
    @LuaFunction
    public LuaJavaObject GetContainedObject()
    {
        return new LuaJavaObject(chunkCoordinates);
    }
}
