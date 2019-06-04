package fr.luacraft.core;

import com.naef.jnlua.LuaState;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Lua net hook manager
 * @author Zino
 */
public class LuaNetHookManager
{
    /**
     * Hooks hash map
     */
    private static HashMap<String, ArrayList<Integer>> hooks = new HashMap<String, ArrayList<Integer>>();

    /**
     * Add a net hook
     * @param message
     * @param func
     */
    public static void add(String message, int func)
    {
        if(hooks.get(message) != null)
        {
            hooks.get(message).add(func);
        }
        else
        {
            hooks.put(message, new ArrayList<Integer>());
            add(message, func);
        }
    }

    /**
     * Call message hooks
     * @param message
     */
    public static void call(String message, Object... args)
    {
        LuaState l = Luacraft.getInstance().getProxy().getLuaState();

        if(hooks.get(message) != null)
        {
            for(int function : hooks.get(message))
            {
                l.rawGet(LuaState.REGISTRYINDEX, function);
                for(Object obj : args)
                {
                    l.pushJavaObject(obj);
                }

                l.call(args.length, 0);
            }
        }
    }
}
