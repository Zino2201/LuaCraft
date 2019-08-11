package fr.luacraft.core.api.util;

import com.naef.jnlua.LuaState;
import fr.luacraft.core.Luacraft;
import fr.luacraft.core.api.ILuaObject;
import fr.luacraft.core.api.reflection.LuaJavaObject;

import java.util.HashMap;

/**
 * Represents a lua class
 * @author Zino
 */
@Deprecated
public class LuaClass implements ILuaObject
{
    private HashMap<String, Integer> functions;

    public LuaClass()
    {
        this.functions = new HashMap<String, Integer>();
    }

    public void AddFunction(String name, int func)
    {
        functions.put(name, func);
    }

    public void CallFunction(String name, Object... args)
    {
        CallFunction(name, 0, args);
    }

    public Object[] CallFunction(String name, int returnCount, Object... args)
    {
        LuaState l = Luacraft.getInstance().getProxy().getLuaState();

        l.rawGet(LuaState.REGISTRYINDEX, functions.get(name));
        for(Object obj : args)
        {
            l.pushJavaObject(obj);
        }

        l.call(args.length, returnCount);
        if(returnCount > 0)
        {
            Object[] out = new Object[returnCount];
            for(int i = 0; i < returnCount - 1; i++)
            {
                out[i] = l.checkJavaObject(i, Object.class);
            }

            return out;
        }

        return null;
    }

    @Override
    public String GetType()
    {
        return "Class";
    }

    @Override
    public boolean IsContainer()
    {
        return false;
    }

    @Override
    public LuaJavaObject GetContainedObject()
    {
        return null;
    }
}
