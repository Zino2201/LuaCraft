package fr.luacraft.modloader.scripts;

import com.naef.jnlua.LuaState;
import fr.luacraft.core.Luacraft;
import fr.luacraft.core.api.meta.blocks.LuaBlockMeta;
import fr.luacraft.modloader.LuaScript;
import fr.luacraft.util.LuaUtil;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * A script representing a block
 */
public class LuaScriptBlock implements ILuaScriptType
{
    @Override
    public String getTypeName()
    {
        return "Block";
    }

    @Override
    public String getDirectoryName()
    {
        return "blocks";
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
                "_Block_" + StringUtils.capitalize(FilenameUtils.removeExtension(script.getFile().getName()));
        LuaBlockMeta.createBlockMetaClassBase(l, meta);
        l.setGlobal("BLOCK");

        Luacraft.getInstance().getProxy().executeScript(script);

        LuaBlockMeta.registerBlock(l, meta);
        LuaUtil.deleteGlobal(l, meta);
        LuaUtil.resetStack(l);
    }
}
