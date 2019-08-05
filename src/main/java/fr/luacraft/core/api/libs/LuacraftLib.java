package fr.luacraft.core.api.libs;

import com.naef.jnlua.JavaFunction;
import com.naef.jnlua.LuaState;
import fr.luacraft.core.Luacraft;
import fr.luacraft.core.api.util.LuaClass;
import fr.luacraft.core.api.LuaMod;
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
     * Create class
     */
    public static JavaFunction CreateClass = new JavaFunction()
    {
        @Override
        public int invoke(LuaState l)
        {
            LuaClass clazz = new LuaClass();
            l.pushJavaObject(clazz);
            return 1;
        }
    };

    /**
     * Get function ref
     */
    public static JavaFunction GetFunctionRef = new JavaFunction()
    {
        @Override
        public int invoke(LuaState l)
        {
            l.pushNumber(l.ref(LuaState.REGISTRYINDEX));
            return 1;
        }
    };


    /**
     * initialize the library
     * @param l
     */
    public static void initialize(LuaState l)
    {
        /** Global scope */
        l.pushJavaFunction(Include);
        l.setGlobal("include");

        /** Luacraft table */
        l.newTable();

        l.pushJavaFunction(GetMod);
        l.setField(-2, "GetMod");
        l.pushJavaFunction(CreateClass);
        l.setField(-2, "CreateClass");
        l.pushJavaFunction(GetFunctionRef);
        l.setField(-2, "GetFunctionRef");

        l.setGlobal("luacraft");
    }
}
