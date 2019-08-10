package fr.luacraft.util;

import com.naef.jnlua.LuaState;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.ForgeDirection;

import java.io.*;

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
}
