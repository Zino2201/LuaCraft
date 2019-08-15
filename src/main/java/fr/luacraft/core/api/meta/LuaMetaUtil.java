package fr.luacraft.core.api.meta;

import com.naef.jnlua.JavaFunction;
import com.naef.jnlua.LuaState;
import fr.luacraft.core.Luacraft;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * Utils for manipulating and creating metatable
 * See {@link ILuaMetaContainer}
 * @author Zino
 */
public class LuaMetaUtil
{
    private static HashMap<Class, String> metaClasses = new HashMap<Class, String>();

    /**
     * __index function
     */
    private static JavaFunction __index = l ->
    {
        Object self = l.checkUserdata(1);

        l.getMetatable(1);
        l.pushValue(2);

        l.pushValue(-1);
        l.getTable( -3);
        if (!l.isNil(-1))
            return 1;

        return 0;
    };

    private static JavaFunction __call = l ->
    {
        Object self = l.checkUserdata(1);

        l.getMetatable(1);
        l.pushValue(2);

        l.pushValue(-1);
        l.getTable( -3);
        if (!l.isNil(-1))
            return 1;

        return 0;
    };

    /**
     * Add basic metamethods for current metatable (__index, __gc and __newindex)
     */
    public static void addBasicMetamethods()
    {
        LuaState l = Luacraft.getInstance().getProxy().getLuaState();

        /** Get jnlua.Object's __gc function to set it */
        newMetatable("jnlua.Object");
        l.getField(-1, "__gc");
        l.remove(-2);
        l.setField(-2, "__gc");

        /** Add __index */
        pushJavaFunction("__index", __index);
    }

    /**
     * Push a java function
     * @param name
     * @param func
     */
    public static void pushJavaFunction(String name, JavaFunction func)
    {
        Luacraft.getInstance().getProxy().getLuaState().pushJavaFunction(func);
        Luacraft.getInstance().getProxy().getLuaState().setField(-2, name);
    }

    /**
     * Terminate a metatable
     */
    public static void closeMetaStatement()
    {
        Luacraft.getInstance().getProxy().getLuaState().pop(1);
    }

    /**
     * Create a new metatable
     * Adapted from lauxlib.c luaL_newmetatable
     * @param name
     */
    public static void newMetatable(String name)
    {
        LuaState l = Luacraft.getInstance().getProxy().getLuaState();

        l.pushValue(l.REGISTRYINDEX);
        l.getField(-1, name);
        if (l.isNil(-1))
        {
            l.pop(1);
            l.newTable();
            l.setField(-2, name);
            l.getField(-1, name);
        }
        l.remove(-2);
    }

    /**
     * Push a javaobject associated with a metadata
     * @param object
     * @param meta
     */
    public static void pushJavaObject(Object object, String meta)
    {
        LuaState l = Luacraft.getInstance().getProxy().getLuaState();

        l.pushUserdata(object);
        getMetatable(meta);
        l.setMetatable(-2);
    }

    public static void getMetatable(String name)
    {
        LuaState l = Luacraft.getInstance().getProxy().getLuaState();

        l.getField(l.REGISTRYINDEX, name);
    }

    public static void createMetamethodsFromClass(Class clazz)
    {
        Method[] mthds = clazz.getMethods();
        for(Method method : mthds)
        {
            String methodName = StringUtils.capitalize(method.getName());
            pushJavaFunction(methodName, new LuaMetaClassMethod(method)
            {
                @Override
                public int invoke(LuaState l)
                {
                    if(method.isAnnotationPresent(LuaMetaHook.class))
                    {
                        Object self = l.checkUserdata(1, Object.class);
                        Object[] args = new Object[method.getParameterCount()];
                        for (int i = 0; i < args.length; i++)
                        {
                            Class clazz = method.getParameterTypes()[i];

                            if (l.isUserdata(2 + i))
                                args[i] = l.checkUserdata(2 + i, clazz);
                            else if (l.isJavaObject(2 + i, clazz))
                                args[i] = l.checkJavaObject(2 + i, clazz);
                        }

                        try
                        {
                            Object ret = method.invoke(self, args);
                            l.pushJavaObject(ret);
                            return 1;
                        }
                        catch (IllegalAccessException | InvocationTargetException e)
                        {
                            e.printStackTrace();
                        }
                    }

                    l.pushNil();
                    return 1;
                }
            });
        }
    }

    public static void createMetaForClass(Class clazz, String meta)
    {
        LuaState l = Luacraft.getInstance().getProxy().getLuaState();

        /** If table exists, don't do anything */
        l.pushValue(l.REGISTRYINDEX);
        l.getField(-1, meta);
        if(!l.isNil(-1))
            return;

        /** Create metatable, and using reflection create {@link LuaMetaClassMethod} JavaFunctions
         for each Java method */
        newMetatable(meta);
        addBasicMetamethods();
        createMetamethodsFromClass(clazz);
        closeMetaStatement();

        metaClasses.put(clazz, meta);
    }

    public static void addMetatableForClass(Class clazz, String metatable)
    {
        metaClasses.put(clazz, metatable);
    }

    /**
     * Get metatable name for object
     * @param object
     * @return
     */
    public static String getMetatableForObject(Object object)
    {
        for(Class clazz : metaClasses.keySet())
        {
            if(clazz.isInstance(object))
                return metaClasses.get(clazz);
        }

        return null;
    }

    /**
     * Get value from metatable
     * @param l
     * @param name
     * @param var
     * @param varType
     * @param <T>
     * @return
     */
    public static <T> T getValueFromMetatable(LuaState l, String name, String var, Class<T> varType)
    {
        l.getField(l.REGISTRYINDEX, name);
        l.getField(-1, var);
        return l.toJavaObject(-1, varType);
    }
}
