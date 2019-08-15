package fr.luacraft.core.api.hooks;

import com.naef.jnlua.LuaState;
import fr.luacraft.core.api.meta.LuaMetaUtil;

/**
 * Lua hook manager
 *
 * @author Zino
 */
public class LuaHookManager
{
    public static Object[] call(LuaState l, String event, Object... params)
    {
        l.getGlobal("hook");
        l.getField(-1, "Call");
        l.pushString(event);
        for(Object object : params)
            LuaMetaUtil.pushJavaObject(object, LuaMetaUtil.getMetatableForObject(object));
        l.call(params != null ? params.length + 1 : 1, 6);

        Object[] returns = new Object[6];
        for(int i = 1; i < 7; i++)
            returns[i - 1] = l.toUserdata(-i);

        l.pop(6);

        return returns;
    }
}
