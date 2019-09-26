package fr.luacraft.core.api.meta.items;

import com.naef.jnlua.LuaState;
import cpw.mods.fml.common.registry.GameRegistry;
import fr.luacraft.core.api.hooks.LuaHookManager;
import fr.luacraft.core.api.items.LuacraftItem;
import fr.luacraft.core.api.meta.LuaMetaClass;
import fr.luacraft.core.api.meta.LuaMetaUtil;
import net.minecraft.item.Item;

import java.util.HashMap;

@LuaMetaClass
public class LuaItemMeta
{
    private static HashMap<String, String> idMetaMap = new HashMap<>();

    public static void initialize(LuaState l)
    {
        LuaMetaUtil.newMetatable("Item");
        LuaMetaUtil.addBasicMetamethods();
        LuaMetaUtil.createMetamethodsFromClass(Item.class);
        LuaMetaUtil.closeMetaStatement();
    }

    public static void createItemMetaClassBase(LuaState l, String meta)
    {
        LuaMetaUtil.newMetatable(meta);
        //LuaMetaUtil.createMetamethodsFromClass(Block.class);
        l.pushString("unnamed");
        l.setField(-2, "UnlocalizedName");
        l.pushInteger(1);
        l.setField(-2, "Material");
        l.pushString("tabBuildingBlocks");
        l.setField(-2, "CreativeTab");
    }

    public static void registerItem(LuaState l, String metaClass)
    {
        /** Get block main properties */
        String unlocalizedName = LuaMetaUtil.getValueFromMetatable(l,
                metaClass, "UnlocalizedName", String.class);

        /** Create block */
        Item item = new LuacraftItem(unlocalizedName);

        /** Add to id-meta map */
        idMetaMap.put(unlocalizedName, metaClass);

        /** Set some properties */


        /** Call inits hooks */
        LuaHookManager.callMetatable(l, "PreInit", metaClass);
        GameRegistry.registerItem(item, unlocalizedName);
        LuaHookManager.callMetatable(l, "PostInit", metaClass);
    }


    public static String getMetaClassForItem(String unlocalizedName)
    {
        return idMetaMap.get(unlocalizedName.replace("item.", ""));
    }
}
