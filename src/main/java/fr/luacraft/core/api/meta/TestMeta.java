package fr.luacraft.core.api.meta;

import com.naef.jnlua.JavaFunction;
import com.naef.jnlua.LuaState;
import fr.luacraft.core.api.reflection.LuaJavaObject;

@LuaMetaClass
public class TestMeta implements ILuaMetaContainer
{
    class TestObj
    {

    }

    public static JavaFunction Test = new JavaFunction()
    {
        public int invoke(LuaState l)
        {
            System.out.println("nnn");
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

    @Override
    public LuaJavaObject GetContainedObject()
    {
        return new LuaJavaObject(new TestObj());
    }

    @Override
    public String GetTypeName()
    {
        return "TestMeta";
    }
}
