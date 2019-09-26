package fr.luacraft.core.api.meta.world;

import com.naef.jnlua.LuaState;
import fr.luacraft.core.api.meta.LuaMetaClass;
import fr.luacraft.core.api.meta.LuaMetaUtil;
import fr.luacraft.core.api.world.LuacraftWorld;

@LuaMetaClass
public class LuaWorldMeta
{
    public static void initialize(LuaState l)
    {
        LuaMetaUtil.createMetaForClass(LuacraftWorld.class, "World");
    }
}
