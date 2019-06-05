package fr.luacraft.core.api;

import com.naef.jnlua.LuaState;
import fr.luacraft.core.Luacraft;

import java.util.HashMap;

/**
 * Represents a lua class
 * @author Zino
 */
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
        LuaState l = Luacraft.getInstance().getProxy().getLuaState();

        l.rawGet(LuaState.REGISTRYINDEX, functions.get(name));
        for(Object obj : args)
        {
            l.pushJavaObject(obj);
        }

        l.call(args.length, 0);
    }

    @Override
    public String GetType()
    {
        return "Class";
    }

    @Override
    public boolean isContainer()
    {
        return false;
    }

    @Override
    public Object getObject()
    {
        return null;
    }
}
