package fr.luacraft.core.api.meta.blocks;

import com.naef.jnlua.LuaState;
import cpw.mods.fml.common.registry.GameRegistry;
import fr.luacraft.core.api.blocks.LuacraftBlock;
import fr.luacraft.core.api.meta.LuaMetaClass;
import fr.luacraft.core.api.meta.LuaMetaUtil;
import net.minecraft.block.Block;

/**
 * Meta class + utils for Block
 */
@LuaMetaClass
public class LuaBlockMeta
{
    public static void initialize(LuaState l)
    {
        LuaMetaUtil.newMetatable("Block");
        LuaMetaUtil.addBasicMetamethods();
        LuaMetaUtil.createMetamethodsFromClass(Block.class);
        LuaMetaUtil.closeMetaStatement();
    }

    public static void createBlockMetaClassBase(LuaState l, String meta)
    {
        LuaMetaUtil.newMetatable(meta);
        //LuaMetaUtil.createMetamethodsFromClass(Block.class);
        l.pushString("unnamed");
        l.setField(-2, "UnlocalizedName");
        l.pushInteger(1);
        l.setField(-2, "Material");
    }

    public static void registerBlock(LuaState l, String metaClass)
    {
        /** Get block main properties */
        String unlocalizedName = LuaMetaUtil.getValueFromMetatable(l,
                metaClass, "UnlocalizedName", String.class);

        int material = LuaMetaUtil.getValueFromMetatable(l,
                metaClass, "Material", Integer.class);

        Block block = new LuacraftBlock(unlocalizedName, material, null);
        /** Set some properties */

        GameRegistry.registerBlock(block, unlocalizedName);
    }
}
