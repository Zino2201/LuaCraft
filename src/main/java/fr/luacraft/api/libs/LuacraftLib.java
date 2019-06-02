package fr.luacraft.api.libs;

import com.naef.jnlua.JavaFunction;
import com.naef.jnlua.LuaState;
import fr.luacraft.api.LuaMod;
import fr.luacraft.core.Luacraft;
import fr.luacraft.util.LuaUtil;

import java.io.File;

/**
 * Base luacraft library
 * luacraft.* and global types
 * @author Zino
 */
public class LuacraftLib
{
    public static JavaFunction Include = new JavaFunction()
    {
        @Override
        public int invoke(LuaState l)
        {
            String file = l.checkString(1);
            File target = new File(LuaUtil.getRunningScript().getParent(), file);
            LuaUtil.runFromFile(l, target);
            return 0;
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
        l.pushJavaFunction(Include);
        l.setGlobal("include");

        /** Luacraft table */
        l.newTable();

        l.pushJavaFunction(GetMod);
        l.setField(-2, "getMod");

        l.setGlobal("luacraft");
    }
}
