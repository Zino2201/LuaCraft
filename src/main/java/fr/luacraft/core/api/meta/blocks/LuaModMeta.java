package fr.luacraft.core.api.meta.blocks;

import com.naef.jnlua.LuaState;
import fr.luacraft.core.api.meta.LuaMetaClass;
import fr.luacraft.core.api.meta.LuaMetaUtil;

@LuaMetaClass
public class LuaModMeta
{
    public static void initialize(LuaState l)
    {
        LuaMetaUtil.newMetatable("Mod");
        LuaMetaUtil.addBasicMetamethods();
        LuaMetaUtil.closeMetaStatement();
    }

    public static void createModMetaBaseClass(LuaState l, String meta)
    {
        LuaMetaUtil.newMetatable(meta);
        l.pushString("unnamed");
        l.setField(-2, "UnlocalizedName");
        l.pushInteger(1);
        l.setField(-2, "Material");
        l.pushString("tabBuildingBlocks");
        l.setField(-2, "CreativeTab");
    }
}
