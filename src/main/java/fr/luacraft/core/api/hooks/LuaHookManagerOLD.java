package fr.luacraft.core.api.hooks;

import com.naef.jnlua.LuaState;
import fr.luacraft.core.Luacraft;
import fr.luacraft.core.api.meta.LuaMetaUtil;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @deprecated OLD HOOK MANAGER, DON'T DELETE YET
 *
 * Manage lua hooks
 * @author Zino
 */
@Deprecated
public class LuaHookManagerOLD
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
     * Call the specified hook
     * @param object
     * @param name
     * @param args
     * @return
     */
    public static void call(Object object, String name, Object... args)
    {
        call(null, object, name, args);
    }

    /**
     * Call the specified hook and get its return value
     * @param clazz
     * @param object
     * @param name
     * @param args
     * @param <T>
     * @return
     */
    public static <T> T call(Class<T> clazz, Object object, String name, Object... args)
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

                    l.call(args != null ? args.length : 0, clazz == null ? 0 : 1);

                    if(clazz != null)
                        if(l.isJavaObject(-1, clazz))
                            return l.toJavaObject(-1, clazz);
                }
            }


        }

        String objectMeta = LuaMetaUtil.getMetatableForObject(object);
        if(objectMeta != null && !objectMeta.equals("jnlua.Object"))
        {
            l.getField(l.REGISTRYINDEX, objectMeta);
            if(l.isNil(-1))
                return null;
            l.getField(-1, name);

            LuaMetaUtil.pushJavaObject(object, objectMeta);
            for (Object obj : args)
            {
                if(LuaMetaUtil.getMetatableForObject(obj) != null)
                    LuaMetaUtil.pushJavaObject(obj, LuaMetaUtil.getMetatableForObject(object));
                else
                    l.pushJavaObject(obj);
            }

            l.call(args != null ? args.length + 1 : 1, clazz == null ? 0 : 1);
            if (clazz != null)
                if (l.isJavaObject(-1, clazz))
                {
                    T obj = l.toJavaObject(-1, clazz);
                    l.pop(1);
                    return obj;
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