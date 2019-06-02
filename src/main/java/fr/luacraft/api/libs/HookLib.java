package fr.luacraft.api.libs;

import com.naef.jnlua.JavaFunction;
import com.naef.jnlua.LuaState;
import fr.luacraft.core.LuaHookManager;
import fr.luacraft.modloader.ILuaObject;

/**
 * Hook library
 * @author Zino
 */
public class HookLib
{
    /**
     * Register a hook
     */
    public static JavaFunction AddHook = new JavaFunction()
    {
        @Override
        public int invoke(LuaState l)
        {
            ILuaObject object = l.checkJavaObject(1, ILuaObject.class);
            String name = l.checkString(2);
            int func = l.ref(LuaState.REGISTRYINDEX);
            LuaHookManager.add(object.getObject(), name, func);

            return 0;
        }
    };

    /**
     * Call a hook
     */
    public static JavaFunction CallHook = new JavaFunction()
    {
        @Override
        public int invoke(LuaState l)
        {
            ILuaObject object = l.checkJavaObject(1, ILuaObject.class);
            String name = l.checkString(2);

            LuaHookManager.call(object, name);

            return 0;
        }
    };

    public static void Initialize(LuaState l)
    {
        /** Hook table */
        l.newTable();

        l.pushJavaFunction(AddHook);
        l.setField(-2, "Add");

        l.pushJavaFunction(CallHook);
        l.setField(-2, "Call");

        l.setGlobal("hook");
    }
}
