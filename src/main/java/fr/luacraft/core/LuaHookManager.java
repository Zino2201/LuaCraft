package fr.luacraft.core;

import com.naef.jnlua.LuaState;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Manage lua hooks
 * @author Zino
 */
public class LuaHookManager
{
    public static HashMap<Object, HashMap<String, ArrayList<Integer>>> hooks = new HashMap<Object, HashMap<String, ArrayList<Integer>>>();

    /**
     * Add a hook to the specified object
     * @param object
     * @param name
     * @param func
     */
    public static void add(Object object, String name, int func)
    {
        if(hooks.get(object) != null)
        {
            if(hooks.get(object).get(name) != null)
            {
                hooks.get(object).get(name).add(func);
            }
            else
            {
                hooks.get(object).put(name, new ArrayList<Integer>());
                add(object, name, func);
            }
        }
        else
        {
            hooks.put(object, new HashMap<String, ArrayList<Integer>>());
            add(object, name, func);
        }
    }

    /**
     * Call a hook using the specified object
     * @param object
     * @param name
     * @param args
     */
    public static void call(Object object, String name, Object... args)
    {
        LuaState l = Luacraft.getInstance().getProxy().getLuaState();

        if(hooks.get(object) != null)
        {
            if(hooks.get(object).get(name) != null)
            {
                for(int function : hooks.get(object).get(name))
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
}
