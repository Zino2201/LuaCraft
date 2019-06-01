package fr.luacraft.api;

import fr.luacraft.modloader.ILuaObject;
import net.minecraft.world.World;

public class LuaWorld implements ILuaObject
{
    private World world;

    public LuaWorld(World world)
    {
        this.world = world;
    }

    @Override
    public Object getObject()
    {
        return world;
    }

    @Override
    public String GetType()
    {
        return "World";
    }
}