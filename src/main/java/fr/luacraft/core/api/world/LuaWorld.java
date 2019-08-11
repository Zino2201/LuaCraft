package fr.luacraft.core.api.world;

import com.naef.jnlua.util.LuaFunction;
import fr.luacraft.core.api.ILuaObject;
import fr.luacraft.core.api.reflection.LuaJavaObject;
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
    @LuaFunction
    public LuaJavaObject GetContainedObject()
    {
        return new LuaJavaObject(world);
    }

    @Override
    @LuaFunction
    public boolean IsContainer()
    {
        return true;
    }

    @Override
    @LuaFunction
    public String GetType()
    {
        return "World";
    }
}
