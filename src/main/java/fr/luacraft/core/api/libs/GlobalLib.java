package fr.luacraft.core.api.libs;

import com.naef.jnlua.JavaFunction;
import com.naef.jnlua.LuaState;

/**
 * This lib is not contained in a table
 */
@LuaLibrary
public class GlobalLib
{
    private static JavaFunction IsString = l ->
    {
        l.pushBoolean(l.toString(1) != null);

        return 1;
    };

    public static void initialize(LuaState l)
    {
        l.pushJavaFunction(IsString);
        l.setGlobal("IsString");
    }
}
