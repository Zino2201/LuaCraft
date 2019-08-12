package fr.luacraft.core.api.world;

import com.naef.jnlua.util.LuaFunction;
import fr.luacraft.core.api.ILuaContainer;
import fr.luacraft.core.api.reflection.LuaJavaObject;
import net.minecraft.world.Explosion;

/**
 * Container for Explosion
 * @author Zino
 */
public class LuaExplosion implements ILuaContainer
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
    public String GetTypeName()
    {
        return "Explosion";
    }

    @Override
    @LuaFunction
    public LuaJavaObject GetContainedObject()
    {
        return new LuaJavaObject(explosion);
    }
}
