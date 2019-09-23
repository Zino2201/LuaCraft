package fr.luacraft.core.api.meta;

import com.naef.jnlua.JavaFunction;
import com.naef.jnlua.LuaState;

import java.lang.reflect.Method;

/**
 * A special {@link JavaFunction} that contains a {@link Method} object
 *
 * @author Zino
 */
public abstract class LuaMetaClassMethod implements JavaFunction
{
    protected Method method;

    public LuaMetaClassMethod(Method method)
    {
        this.method = method;
    }

    @Override
    public abstract int invoke(LuaState l);
}