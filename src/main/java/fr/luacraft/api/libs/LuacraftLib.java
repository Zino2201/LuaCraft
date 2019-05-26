package fr.luacraft.api.libs;

import com.naef.jnlua.JavaFunction;
import com.naef.jnlua.LuaState;
import fr.luacraft.api.LuaVector3;

/**
 * Base luacraft library
 * luacraft.* and global types
 */
public class LuacraftLib
{
    /**
     * Create a Vector3
     */
    public static JavaFunction Vector3 = new JavaFunction()
    {
        @Override
        public int invoke(LuaState l)
        {
            LuaVector3 vec = new LuaVector3(l.checkInteger(1, 0), l.checkInteger(2, 0), l.checkInteger(3, 0));
            l.pushJavaObject(vec);
            return 1;
        }
    };

    /**
     * Find a meta table
     */
    public static JavaFunction FindMetaTable = new JavaFunction()
    {
        @Override
        public int invoke(LuaState l)
        {
            String meta = l.checkString(1);
            l.getField(LuaState.REGISTRYINDEX, meta);
            l.getMetatable(-2);
            return 1;
        }
    };

    /**
     * Initialize the library
     * @param l
     */
    public static void Initialize(LuaState l)
    {
        /** Global scope */
        l.pushJavaFunction(Vector3);
        l.setGlobal("Vector3");

        /** Luacraft table */
        l.newTable();

        l.pushJavaFunction(FindMetaTable);
        l.setField(-2, "findMetaTable");

        l.setGlobal("luacraft");
    }
}
