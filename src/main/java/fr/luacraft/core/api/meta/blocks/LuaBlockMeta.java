package fr.luacraft.core.api.meta.blocks;

import com.naef.jnlua.LuaState;
import cpw.mods.fml.common.registry.GameRegistry;
import fr.luacraft.core.api.blocks.LuacraftBlock;
import fr.luacraft.core.api.hooks.LuaHookManager;
import fr.luacraft.core.api.meta.LuaMetaClass;
import fr.luacraft.core.api.meta.LuaMetaUtil;
import net.minecraft.block.Block;

import java.util.HashMap;

/**
 * Meta class + utils for Block
 */
@LuaMetaClass
public class LuaBlockMeta
{
    private static HashMap<String, String> idMetaMap = new HashMap<>();

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
        l.pushString("tabBuildingBlocks");
        l.setField(-2, "CreativeTab");
        l.pushNumber(0);
        l.setField(-2, "Hardness");
        l.pushNumber(0);
        l.setField(-2, "LightLevel");
        l.pushNumber(0);
        l.setField(-2, "Resistance");
        l.pushInteger(0);
        l.setField(-2, "LightOpacity");
        l.pushBoolean(false);
        l.setField(-2, "UnBreakable");
    }

    public static void registerBlock(LuaState l, String metaClass)
    {
        /** Get block main properties */
        String unlocalizedName = LuaMetaUtil.getValueFromMetatable(l,
                metaClass, "UnlocalizedName", String.class);

        int material = LuaMetaUtil.getValueFromMetatable(l,
                metaClass, "Material", Integer.class);

        /** Create block */
        Block block = new LuacraftBlock(unlocalizedName, material, null);

        /** Add to id-meta map */
        idMetaMap.put(unlocalizedName, metaClass);

        /** Set some properties */
        block.setHardness(LuaMetaUtil.getValueFromMetatable(l, metaClass, "Hardness", Float.class));
        block.setLightLevel(LuaMetaUtil.getValueFromMetatable(l, metaClass, "LightLevel", Float.class));
        block.setResistance(LuaMetaUtil.getValueFromMetatable(l, metaClass, "Resistance", Float.class));
        block.setLightOpacity(LuaMetaUtil.getValueFromMetatable(l, metaClass, "LightOpacity", Integer.class));

        if(LuaMetaUtil.getValueFromMetatable(l, metaClass, "UnBreakable", Boolean.class))
            block.setBlockUnbreakable();

        /** Call inits hooks */
        LuaHookManager.callMetatable(l, "PreInit", metaClass);
        GameRegistry.registerBlock(block, unlocalizedName);
        LuaHookManager.callMetatable(l, "PostInit", metaClass);
    }

    public static String getMetaClassForBlock(String unlocalizedName)
    {
        return idMetaMap.get(unlocalizedName.replace("tile.", ""));
    }
}
