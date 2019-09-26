package fr.luacraft.modloader.scripts;

import com.naef.jnlua.LuaState;
import fr.luacraft.core.Luacraft;
import fr.luacraft.core.api.meta.items.LuaItemMeta;
import fr.luacraft.modloader.LuaScript;
import fr.luacraft.util.LuaUtil;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

public class LuaScriptItem implements ILuaScriptType
{
    @Override
    public String getTypeName()
    {
        return "Item";
    }

    @Override
    public String getDirectoryName()
    {
        return "items";
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
        String meta = Luacraft.getInstance().getModLoader().getCurrentMod().getMetaName() +
                "_Item_" + StringUtils.capitalize(FilenameUtils.removeExtension(script.getFile().getName()));
        LuaItemMeta.createItemMetaClassBase(l, meta);
        l.setGlobal("ITEM");

        Luacraft.getInstance().getProxy().executeScript(script);

        LuaItemMeta.registerItem(l, meta);
        LuaUtil.deleteGlobal(l, meta);
        LuaUtil.resetStack(l);
    }
}
