package fr.luacraft.core.api.util;

import com.naef.jnlua.LuaState;
import fr.luacraft.core.Luacraft;
import fr.luacraft.core.api.ILuaContainer;
import fr.luacraft.core.api.reflection.LuaJavaObject;

import java.util.HashMap;

import static com.naef.jnlua.LuaState.REGISTRYINDEX;
import static fr.luacraft.core.Luacraft.getInstance;

/**
 * Represents a lua class
 * @author Zino
 */
@Deprecated
public class LuaClass implements ILuaContainer
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
        LuaState l = getInstance().getProxy().getLuaState();

        l.rawGet(REGISTRYINDEX, functions.get(name));
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
    public String GetTypeName()
    {
        return "Class";
    }

    @Override
    public LuaJavaObject GetContainedObject()
    {
        return null;
    }
}
