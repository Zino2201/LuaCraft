package fr.luacraft.core.api.hooks;

import com.naef.jnlua.LuaState;
import fr.luacraft.core.api.meta.LuaMetaUtil;
import fr.luacraft.util.LuaUtil;
import org.apache.commons.lang3.ArrayUtils;

/**
 * Lua hook manager
 *
 * @author Zino
 */
public class LuaHookManager
{
    public static void add(LuaState l, String event, Object id, int function)
    {
        l.getGlobal("hook");
        l.getField(-1, "Add");
        l.pushString(event);
        l.pushJavaObject(id);
        l.pushValue(function);

        l.setTop(0);
    }

    public static Object[] call(LuaState l, String event, Object... params)
    {
        l.setTop(0);

        l.getGlobal("hook");
        l.getField(-1, "Call");
        l.pushString(event);
        for(Object object : params)
            LuaMetaUtil.pushJavaObject(object, LuaMetaUtil.getMetatableForObject(object));
        l.call(params != null ? params.length + 1 : 1, 6);

        Object[] returns = new Object[6];
        for(int i = 1; i < 7; i++)
            returns[i - 1] = l.toUserdata(-i);

        l.setTop(0);

        return returns;
    }

    /**
     * Call a hook using a metatable
     * @param l
     * @param event
     * @param metaTable
     * @param params
     * @return
     */
    public static Object callMetatable(LuaState l, String event, String metaTable,
                                     Object... params)
    {
        LuaUtil.resetStack(l);

        l.getGlobal("hook");
        l.getField(-1, "CallTable");
        l.pushString(event);
        l.pushString(metaTable);
        for(Object object : params)
            LuaMetaUtil.pushJavaObject(object, LuaMetaUtil.getMetatableForObject(object));
        l.call(params.length + 2, 6);

        Object ret = l.toJavaObject(-1, Object.class);

        LuaUtil.resetStack(l);

        return ret;
    }
}
