package fr.luacraft.core.api.world;

import fr.luacraft.core.api.ILuaObject;
import net.minecraft.world.World;

/**
 * Represents a World in Lua
 * @author Zino
 */
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
    public boolean isContainer()
    {
        return true;
    }

    @Override
    public String GetType()
    {
        return "World";
    }
}
