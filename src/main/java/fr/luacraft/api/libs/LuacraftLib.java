package fr.luacraft.api.libs;

import com.naef.jnlua.JavaFunction;
import com.naef.jnlua.LuaState;
import fr.luacraft.api.LuaMod;
import fr.luacraft.core.Luacraft;

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
     * Get mod
     */
    public static JavaFunction GetMod = new JavaFunction()
    {
        @Override
        public int invoke(LuaState l)
        {
            LuaMod mod = new LuaMod(Luacraft.getInstance().getProxy().getCurrentMod());
            l.pushJavaObject(mod);
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

        l.pushJavaFunction(GetMod);
        l.setField(-2, "getMod");

        l.setGlobal("luacraft");
    }
}
