package fr.luacraft.core.api.util;

import com.naef.jnlua.util.LuaFunction;
import fr.luacraft.core.api.ILuaContainer;
import fr.luacraft.core.api.reflection.LuaJavaObject;
import net.minecraft.util.ChunkCoordinates;

public class LuaChunkCoordinates implements ILuaContainer
{
    private ChunkCoordinates chunkCoordinates;

    public LuaChunkCoordinates(ChunkCoordinates chunkCoordinates)
    {
        this.chunkCoordinates = chunkCoordinates;
    }

    @Override
    @LuaFunction
    public String GetTypeName()
    {
        return "ChunkCoordinates";
    }

    @Override
    @LuaFunction
    public LuaJavaObject GetContainedObject()
    {
        return new LuaJavaObject(chunkCoordinates);
    }
}
