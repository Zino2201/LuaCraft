package fr.luacraft.core.api.hooks;

import com.naef.jnlua.LuaState;

import java.util.HashMap;

/**
 * Lua hook manager
 *
 * @author Zino
 */
public class LuaHookManager
{
    private static HashMap<String, HashMap<String, Integer>> hooks = new HashMap<>();

    public static void add(String event, String id, int function)
    {
        if(hooks.get(event) != null)
        {
            for(String ids : hooks.get(event).keySet())
            {
                if(ids.equals(id))
                    return;
            }
        }

        HashMap<String, Integer> eventHashmap = new HashMap<>();
        eventHashmap.put(id, function);
        hooks.put(event, eventHashmap);
    }

    public void call(LuaState l, String event, Object... params)
    {
        if(hooks.get(event) != null)
        {
            for(int function : hooks.get(event).values())
            {
                l.rawGet(LuaState.REGISTRYINDEX, function);
                for(Object obj : params)
                {
                    l.pushJavaObject(obj);
                }

                l.call(params != null ? params.length : 0, 0);
            }
        }
    }
}
