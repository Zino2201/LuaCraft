package fr.luacraft.core.api.libs;

import com.naef.jnlua.JavaFunction;
import com.naef.jnlua.LuaState;
import fr.luacraft.core.api.reflection.LuaField;
import fr.luacraft.core.api.reflection.LuaMethod;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.commons.lang3.reflect.MethodUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

@LuaLibrary
public class ReflLib
{
    public static JavaFunction GetProperties = new JavaFunction()
    {
        @Override
        public int invoke(LuaState l)
        {
            Object object = l.checkJavaObject(1, Object.class);

            /** Get list of fields */
            Field[] fields = FieldUtils.getAllFields(object.getClass());

            /** Push array */
            l.newTable();

            for(int i = 0; i < fields.length; i++)
            {
                l.pushInteger(i);
                l.pushJavaObject(new LuaField(fields[i]));
                l.rawSet(-3);
            }

            return 1;
        }
    };

    public static JavaFunction GetMethods = new JavaFunction()
    {
        @Override
        public int invoke(LuaState l)
        {
            Object object = l.checkJavaObject(1, Object.class);

            /** Get list of fields */
            Method[] mthds = object.getClass().getDeclaredMethods();

            /** Push array */
            l.newTable();

            for(int i = 0; i < mthds.length; i++)
            {
                l.pushInteger(i);
                l.pushJavaObject(new LuaMethod(mthds[i]));
                l.rawSet(-3);
            }

            return 1;
        }
    };


    public static void initialize(LuaState l)
    {
        l.newTable();
        l.pushJavaObject(GetProperties);
        l.setField(-2, "GetProperties");
        l.pushJavaObject(GetMethods);
        l.setField(-2, "GetMethods");
        l.setGlobal("refl");
    }
}
