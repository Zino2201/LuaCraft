package fr.luacraft.modloader.scripts;

import com.naef.jnlua.LuaState;
import fr.luacraft.core.Luacraft;
import fr.luacraft.modloader.LuaScript;

public class LuaScriptAutorun implements ILuaScriptType
{
    @Override
    public String getTypeName()
    {
        return "Autorun";
    }

    @Override
    public String getDirectoryName()
    {
        return "autorun";
    }

    @Override
    public String getParentType()
    {
        return null;
    }

    @Override
    public String getFilename()
    {
        return null;
    }

    @Override
    public void execute(LuaState l, LuaScript script)
    {
        Luacraft.getInstance().getProxy().executeScript(script);
    }
}
