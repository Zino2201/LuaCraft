package fr.luacraft.core.api.world;

import fr.luacraft.core.api.ILuaObject;
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
    public String GetType()
    {
        return "Explosion";
    }

    @Override
    public boolean IsContainer()
    {
        return false;
    }

    @Override
    public Object GetContainedObject()
    {
        return null;
    }
}
