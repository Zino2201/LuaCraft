package fr.luacraft.core.api.libs;

import com.naef.jnlua.JavaFunction;
import com.naef.jnlua.LuaState;
import fr.luacraft.core.api.util.LuaTimerManager;

/**
 * Provide timer capabilities for Lua
 * @author Zino
 */
@LuaLibrary
public class TimerLib
{
    public static JavaFunction Create = new JavaFunction()
    {
        @Override
        public int invoke(LuaState l)
        {
            String name = l.checkString(1);
            double delay = l.checkNumber(2);
            int repetition = (int) l.checkNumber(3);
            int func = l.ref(LuaState.REGISTRYINDEX);

            LuaTimerManager.add(name, delay, repetition, func);

            return 0;
        }
    };

    public static void initialize(LuaState l)
    {
        l.newTable();
        l.pushJavaFunction(Create);
        l.setField(-2, "Create");
        l.setGlobal("timer");
    }
}
