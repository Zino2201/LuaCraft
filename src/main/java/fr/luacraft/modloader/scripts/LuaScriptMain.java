package fr.luacraft.modloader.scripts;

import com.naef.jnlua.LuaState;
import fr.luacraft.core.Luacraft;
import fr.luacraft.core.api.hooks.LuaHookManager;
import fr.luacraft.core.api.meta.blocks.LuaModMeta;
import fr.luacraft.modloader.LuaScript;
import fr.luacraft.util.LuaUtil;

/**
 * Main.lua script type
 */
public class LuaScriptMain implements ILuaScriptType
{
    @Override
    public String getTypeName()
    {
        return "Main";
    }

    @Override
    public String getDirectoryName()
    {
        return null; // Return null to tell that we don't want a specific directory
    }

    @Override
    public String getParentType()
    {
        return null;
    }

    @Override
    public void execute(LuaState l, LuaScript script)
    {
        String metaName = Luacraft.getInstance().getModLoader().getCurrentMod().getMetaName();

        /** Initialize metatable and call script */
        LuaModMeta.createModMetaBaseClass(l, metaName);
        l.setGlobal("MOD");

        Luacraft.getInstance().getProxy().executeScript(script);

        LuaHookManager.callMetatable(l,
                "OnPreInit",
                metaName);

        LuaUtil.deleteGlobal(l, metaName);
        LuaUtil.resetStack(l);
    }

    @Override
    public String getFilename()
    {
        return "main.lua";
    }
}
