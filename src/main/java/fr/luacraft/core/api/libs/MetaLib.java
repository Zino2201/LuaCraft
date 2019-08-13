package fr.luacraft.core.api.libs;

import com.naef.jnlua.JavaFunction;
import com.naef.jnlua.LuaState;
import fr.luacraft.core.api.meta.LuaMetaUtil;

@LuaLibrary
public class MetaLib
{
    private static JavaFunction CreateMetaForClass = l ->
    {
        String clazzName = l.checkString(1);
        String metaName = l.checkString(2);

        try
        {
            Class clazz = Class.forName(clazzName);
            LuaMetaUtil.createMetaForClass(clazz, metaName);
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }

        return 0;
    };

    private static JavaFunction GetMetatable = l ->
    {
        String metaName = l.checkString(1);

        /** Just get the metatable from the registry index REGISTRY[metaname] */
        l.pushValue(l.REGISTRYINDEX);
        l.getField(-1, metaName);
        if(l.isNil(-1))
            l.pushNil();

        return 1;
    };

    public static void initialize(LuaState l)
    {
        l.newTable();
        l.pushJavaObject(CreateMetaForClass);
        l.setField(-2, "CreateMetaForClass");
        l.pushJavaObject(GetMetatable);
        l.setField(-2, "GetMetatable");
        l.setGlobal("meta");
    }
}
