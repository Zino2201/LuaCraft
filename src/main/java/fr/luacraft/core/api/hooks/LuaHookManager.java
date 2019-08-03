package fr.luacraft.core.api.hooks;

import com.naef.jnlua.LuaState;
import fr.luacraft.core.Luacraft;

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

    /**
     * Call the specified hook and get the return value as a boolean
     * @param object
     * @param name
     * @param args
     * @return
     */
    public static boolean callReturn(Object object, String name, Object... args)
    {
        Boolean ret = callReturn(Boolean.class, object, name, args);

        if(ret == null)
            return false;
        else return ret;
    }

    /**
     * Call the specified hook and get the return value
     * @param retType
     * @param object
     * @param name
     * @param args
     * @param <T>
     * @return
     */
    public static <T> T callReturn(Class<T> retType, Object object, String name, Object... args)
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

                    l.call(args.length, 1);

                    if(l.isJavaObject(-1, retType))
                        return l.toJavaObject(-1, retType);
                }
            }
        }

        return null;
    }

    /**
     * Return if a object hook has got binds
     * @param object
     * @param name
     * @return
     */
    public static boolean hasHooks(Object object, String name)
    {
        if(hooks.get(object) != null)
        {
            if(hooks.get(object).get(name) != null)
            {
                if(hooks.get(object).get(name).size() > 0)
                    return true;
            }
        }

        return false;
    }
}
