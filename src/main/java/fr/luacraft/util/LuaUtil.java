package fr.luacraft.util;

import com.naef.jnlua.LuaState;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemStack;

/**
 * Lua utils functions
 * @author Zino
 */
public class LuaUtil
{
    /**
     * Get a item stack from a formatted string (modid:name:meta:count)
     * @param itemstackID
     * @return
     */
    public static ItemStack getItemStackFromStr(String itemstackID)
    {
        // TODO: Switch meta & count

        String[] formattedID = itemstackID.split(":");
        String modid = formattedID[0];
        String id = formattedID[1];
        String stackSize = "1";
        if(formattedID.length > 2 && formattedID[2] != null)
            stackSize = formattedID[2];
        ItemStack stack = GameRegistry.findItemStack(modid, id, Integer.parseInt(stackSize));
        return stack;
    }

    public static void deleteGlobal(LuaState l, String global)
    {
        l.pushNil();
        l.setGlobal(global);
    }

    public static void resetStack(LuaState l)
    {
        l.pop(l.getTop());
    }
}
