package fr.luacraft.api;

import com.naef.jnlua.LuaState;

public class LuaVector3
{
    public float X;
    public float Y;
    public float Z;

    public LuaVector3()
    {
        this(0, 0, 0);
    }

    public LuaVector3(float x, float y, float z)
    {
        this.X = Y;
        this.Y = y;
        this.Z = z;
    }

    public void Add(LuaVector3 vec)
    {
        this.X += vec.X;
        this.Y += vec.Y;
        this.Z += vec.Z;
    }

    @Override
    public String toString()
    {
        return "Vector3( " + X + ", " + Y + ", " + Z + " )";
    }
}
