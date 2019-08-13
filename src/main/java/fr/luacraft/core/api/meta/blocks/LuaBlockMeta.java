package fr.luacraft.core.api.meta.blocks;

import com.naef.jnlua.JavaFunction;
import com.naef.jnlua.LuaState;
import fr.luacraft.core.api.meta.ILuaMetaContainer;
import fr.luacraft.core.api.meta.LuaMetaClass;
import fr.luacraft.core.api.meta.LuaMetaUtil;
import fr.luacraft.core.api.reflection.LuaJavaObject;
import net.minecraft.block.Block;

@LuaMetaClass
public class LuaBlockMeta implements ILuaMetaContainer
{
    private Block block;

    public LuaBlockMeta(Block block)
    {
        this.block = block;
    }

    private static JavaFunction GetUnlocalizedName = l ->
    {
        Block self = (Block) l.checkUserdata(1);

        l.pushString(self.getUnlocalizedName());

        return 1;
    };

    public static void initialize(LuaState l)
    {
        LuaMetaUtil.newMetatable("Block");
        LuaMetaUtil.addBasicMetamethods();
        LuaMetaUtil.pushJavaFunction("GetUnlocalizedName", GetUnlocalizedName);
        LuaMetaUtil.closeMetaStatement();
    }

    public Block getBlock()
    {
        return block;
    }

    @Override
    public LuaJavaObject GetContainedObject()
    {
        return new LuaJavaObject(block);
    }

    @Override
    public String GetTypeName()
    {
        return "Block";
    }
}
