package fr.luacraft.core.api.meta;

import com.naef.jnlua.JavaFunction;
import com.naef.jnlua.LuaState;
import fr.luacraft.core.Luacraft;
import fr.luacraft.core.api.reflection.LuaJavaObject;
import org.apache.commons.lang3.reflect.FieldUtils;

/**
 * Utils for manipulating and creating metatable
 * See {@link ILuaMetaContainer}
 * @author Zino
 */
public class LuaMetaUtil
{
    /**
     * __index function
     * Use reflection
     */
    private static JavaFunction __index = new JavaFunction()
    {
        public int invoke(LuaState l)
        {
            Object self = l.checkUserdata(1);
            String name = l.checkString(2);

            if(self instanceof ILuaMetaContainer)
            {
                try
                {
                    Object calledField = FieldUtils.readField(self, name, true);
                    if(calledField instanceof JavaFunction)
                    {
                        return ((JavaFunction) calledField).invoke(l);
                    }
                    else
                    {
                        l.pushJavaObject(new LuaJavaObject(calledField));

                        return 1;
                    }
                }
                catch (IllegalAccessException e)
                {
                    e.printStackTrace();
                }
            }

            return 0;
        }
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

        /** Check if metatable name is not taken */
        l.pushValue(l.REGISTRYINDEX);
        l.getField(-1, name);
        if(!l.isNil(-1))
            return;

        /** It is not, create a new metatable */
        l.pop(1);
        l.newTable();
        l.pushValue(-1);
        l.setField(l.REGISTRYINDEX, name);
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
        newMetatable(meta);
        l.setMetatable(-2);
    }
}
