package fr.luacraft.core.api.world;

import com.naef.jnlua.util.LuaFunction;
import fr.luacraft.core.api.ILuaObject;
import fr.luacraft.core.api.reflection.LuaJavaObject;
import net.minecraft.world.Explosion;

/**
 * Container for Explosion
 * @author Zino
 */
public class LuaExplosion implements ILuaObject
{
    private Explosion explosion;

    public LuaExplosion(Explosion explosion)
    {
        this.explosion = explosion;
    }

    public Explosion getExplosion()
    {
        return explosion;
    }

    @Override
    @LuaFunction
    public String GetType()
    {
        return "Explosion";
    }

    @Override
    @LuaFunction
    public boolean IsContainer()
    {
        return false;
    }

    @Override
    @LuaFunction
    public LuaJavaObject GetContainedObject()
    {
        return new LuaJavaObject(explosion);
    }
}
