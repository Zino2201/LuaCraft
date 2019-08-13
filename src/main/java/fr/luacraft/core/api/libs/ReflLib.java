package fr.luacraft.core.api.libs;

import com.naef.jnlua.JavaFunction;
import com.naef.jnlua.LuaState;
import fr.luacraft.core.api.reflection.LuaField;
import fr.luacraft.core.api.reflection.LuaJavaObject;
import fr.luacraft.core.api.reflection.LuaMethod;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Reflection library
 *
 * This library allows Lua to get details about Java classes
 * and also call functions, write and set fields
 *
 * @author Zino
 */
@LuaLibrary
public class ReflLib
{
    public static JavaFunction GetProperties = new JavaFunction()
    {
        @Override
        public int invoke(LuaState l)
        {
            LuaJavaObject object = l.checkJavaObject(1, LuaJavaObject.class);

            /** Get list of fields */
            Field[] fields = FieldUtils.getAllFields(object.GetJavaObject().getClass());

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
            LuaJavaObject object = l.checkJavaObject(1, LuaJavaObject.class);

            /** Get list of fields */
            Method[] mthds = object.GetJavaObject().getClass().getDeclaredMethods();

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

    public static JavaFunction GetField = new JavaFunction()
    {
        @Override
        public int invoke(LuaState l)
        {
            LuaJavaObject object = l.checkJavaObject(1, LuaJavaObject.class);
            String fieldName = l.checkJavaObject(2, String.class);
            boolean forceAccess = l.isBoolean(3) ? l.toBoolean(3) : false;

            LuaField field = new LuaField(
                    FieldUtils.getField(object.GetJavaObject().getClass(), fieldName, forceAccess));

            l.pushJavaObject(field);

            return 1;
        }
    };

    public static JavaFunction ReadField = new JavaFunction()
    {
        @Override
        public int invoke(LuaState l)
        {
            LuaJavaObject object = l.checkJavaObject(1, LuaJavaObject.class);
            LuaField fieldObj = null;
            String fieldName = null;
            if(l.isString(2))
                fieldName = l.checkString(2);
            else if(l.isJavaObject(2, LuaField.class))
                fieldObj = l.checkJavaObject(2, LuaField.class);
            boolean forceAccess = l.isBoolean(3) ? l.toBoolean(3) : false;

            LuaJavaObject field = null;

            try
            {
                field = new LuaJavaObject(fieldName != null
                        ? FieldUtils.readField(object.GetJavaObject(), fieldName, forceAccess)
                        : FieldUtils.readField((Field) fieldObj.GetContainedObject().GetJavaObject(),
                                        object.GetJavaObject()));
            }
            catch (IllegalAccessException e)
            {
                e.printStackTrace();
            }

            l.pushJavaObject(field);

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
        l.pushJavaObject(GetField);
        l.setField(-2, "GetField");
        l.pushJavaObject(ReadField);
        l.setField(-2, "ReadField");
        l.setGlobal("refl");
    }
}
