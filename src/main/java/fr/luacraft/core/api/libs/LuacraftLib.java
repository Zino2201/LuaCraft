package fr.luacraft.core.api.libs;

import com.naef.jnlua.JavaFunction;
import com.naef.jnlua.LuaState;
import fr.luacraft.core.Luacraft;
import fr.luacraft.core.api.ILuaContainer;
import fr.luacraft.core.api.ILuaObject;
import fr.luacraft.core.api.blocks.LuacraftBlock;
import fr.luacraft.core.api.meta.LuaMetaUtil;
import fr.luacraft.core.api.modloader.LuaMod;
import fr.luacraft.core.api.registry.LuaGameRegistry;
import fr.luacraft.core.api.util.LuaClass;

/**
 * Base luacraft library
 * luacraft.* and global types
 * @author Zino
 */
@LuaLibrary
public class LuacraftLib
{
    public static JavaFunction Include = new JavaFunction()
    {
        @Override
        public int invoke(LuaState l)
        {
            try {
                throw new Exception("NOT IMPLEMENTED");
            } catch (Exception e) {
                e.printStackTrace();
            }
            // FIXME: Will not work with lua mods inside archives
            //String file = l.checkString(1);
            //File target = new File(Luacraft.getInstance().getProxy().getCurrentScript().getFile().getParent(),
               //     file);
            //Luacraft.getInstance().getProxy().executeScript(new LuaScript(target, target.getName()));
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
            LuaMod mod = new LuaMod(Luacraft.getInstance().getModLoader().getCurrentMod());
            l.pushJavaObject(mod);
            return 1;
        }
    };

    /**
     * Create class
     */
    @Deprecated
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
    @Deprecated
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
     * Get current luacraft version
     */
    public static JavaFunction GetVersion = new JavaFunction()
    {
        @Override
        public int invoke(LuaState l)
        {
            l.pushString(Luacraft.VERSION);
            return 1;
        }
    };

    public static JavaFunction IsContainer = new JavaFunction()
    {
        @Override
        public int invoke(LuaState l)
        {
            ILuaObject object = l.checkJavaObject(1, ILuaObject.class);

            boolean isContainer = false;

            if(object instanceof ILuaContainer)
                isContainer = true;

            l.pushBoolean(isContainer);

            return 1;
        }
    };

    public static JavaFunction Test = new JavaFunction()
    {
        @Override
        public int invoke(LuaState l)
        {
            LuacraftBlock b = new LuacraftBlock("tio", 0, null);
            LuaGameRegistry.registerBlock("tio", b);
            LuaMetaUtil.pushJavaObject(b, "Block");
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
        l.pushJavaFunction(IsContainer);
        l.setField(-2, "IsContainer");
        l.pushJavaFunction(Test);
        l.setField(-2, "Test");

        l.setGlobal("luacraft");
    }
}
