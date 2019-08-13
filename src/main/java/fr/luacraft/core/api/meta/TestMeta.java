package fr.luacraft.core.api.meta;

import com.naef.jnlua.JavaFunction;
import com.naef.jnlua.LuaState;

@LuaMetaClass
public class TestMeta
{
    public static JavaFunction Fuck = new JavaFunction()
    {
        public int invoke(LuaState l) {
            System.exit(0);
            return 0;
        }
    };

    public static void initialize(LuaState l)
    {
        LuaMetaUtil.newMetatable("TestMeta");
        LuaMetaUtil.addBasicMetamethods();
        LuaMetaUtil.pushJavaFunction("Test", Test);
        LuaMetaUtil.closeMetaStatement();
    }
}
