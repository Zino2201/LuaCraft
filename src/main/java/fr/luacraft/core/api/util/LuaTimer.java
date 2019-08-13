package fr.luacraft.core.api.util;

/**
 * Represents a timer created and handled in Lua
 * @author Zino
 */
public class LuaTimer
{
    private String name;
    private double delay;
    private int repetitions;
    private int callback;
    public double currentTime;
    private int leftReptitions;
    private boolean loop;

    public LuaTimer(String name, double delay, int repetitions, int callback)
    {
        this.name = name;
        this.delay = delay;
        this.repetitions = repetitions;
        this.leftReptitions = repetitions;
        this.callback = callback;
        this.loop = repetitions == 0;
        this.currentTime = delay;
    }

    public void tick(long deltaTime)
    {
        currentTime -= deltaTime;

        if(currentTime <= 0)
        {
            if(!loop)
            {
                if(leftReptitions-- <= 0)
                {
                    LuaTimerManager.remove(name);
                    return;
                }
            }

            LuaTimerManager.call(name);
            currentTime = delay;
        }
    }

    public String getName()
    {
        return name;
    }

    public double getDelay()
    {
        return delay;
    }

    public int getRepetitions()
    {
        return repetitions;
    }

    public int getCallback()
    {
        return callback;
    }
}
