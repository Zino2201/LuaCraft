package fr.luacraft.core.api.world;

import com.naef.jnlua.util.LuaFunction;
import fr.luacraft.core.api.ILuaContainer;
import fr.luacraft.core.api.meta.LuaMetaHook;
import fr.luacraft.core.api.reflection.LuaJavaObject;
import net.minecraft.world.World;

/**
 * Represents a World in Lua
 * @author Zino
 */
public class LuacraftWorld
{
    private World world;

    public LuacraftWorld(World world)
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
    @LuaMetaHook
    public boolean IsRemote()
    {
        return world.isRemote;
    }
}
