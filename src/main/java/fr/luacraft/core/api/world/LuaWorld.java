package fr.luacraft.core.api.world;

import com.naef.jnlua.util.LuaFunction;
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

    public World getWorld()
    {
        return world;
    }

    /**
     * Return if world is remote
     * @return
     */
    @LuaFunction
    public boolean IsRemote()
    {
        return world.isRemote;
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
