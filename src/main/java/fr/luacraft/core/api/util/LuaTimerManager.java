package fr.luacraft.core.api.util;

import com.naef.jnlua.LuaState;
import fr.luacraft.core.Luacraft;
import net.minecraft.client.Minecraft;

import java.util.HashMap;

/**
 * Class to manage timed created by Lua scripts
 */
public class LuaTimerManager
{
    private static HashMap<String, LuaTimer> timers = new HashMap<String, LuaTimer>();
    private static long oldTime;
    private static long newTime;
    private static long deltaTime;

    /**
     * Add a new timer
     * @param name
     * @param delay
     * @param repetition
     * @param callback
     */
    public static void add(String name, double delay, int repetition,
                           int callback)
    {
        if(timers.containsKey(name))
            return;

        timers.put(name, new LuaTimer(name, delay, repetition, callback));
    }

    /**
     * Call timer callback
     * @param name
     */
    public static void call(String name)
    {
        if(timers.containsKey(name))
        {
            LuaState l = Luacraft.getInstance().getProxy().getLuaState();

            LuaTimer timer = timers.get(name);
            l.rawGet(LuaState.REGISTRYINDEX, timer.getCallback());
            l.call(0, 0);
        }
    }

    /**
     * Remove a timer
     * @param name
     */
    public static void remove(String name)
    {
        if(timers.containsKey(name))
            timers.remove(name);
    }

    /**
     * Update all timers
     */
    public static void tickTimers()
    {
        oldTime = newTime;
        newTime = Minecraft.getMinecraft().getSystemTime();
        deltaTime = newTime - oldTime;

        for(LuaTimer timer : timers.values())
        {
            timer.tick(deltaTime);
        }
    }
}
