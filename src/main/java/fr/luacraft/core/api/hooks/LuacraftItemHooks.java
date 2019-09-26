package fr.luacraft.core.api.hooks;

import fr.luacraft.core.Luacraft;
import fr.luacraft.core.api.entity.LuaEntityPlayer;
import fr.luacraft.core.api.items.LuaItemStack;
import fr.luacraft.core.api.meta.items.LuaItemMeta;
import fr.luacraft.core.api.world.LuacraftWorld;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Static class to share common Item hooks
    @quthor Zino
 */
public class LuacraftItemHooks
{
    public static boolean onItemUse(Item item, ItemStack stack, EntityPlayer player, World world, int x,
                                    int y, int z, int hand, float hitX, float hitY, float hitZ)
    {
        String meta = LuaItemMeta.getMetaClassForItem(item.getUnlocalizedName());
        Boolean bool = (Boolean) LuaHookManager.callMetatable(
                Luacraft.getInstance().getProxy().getLuaState(),
                "OnItemUse",
                meta,
                stack,
                player,
                world,
                x,
                y,
                z,
                hand,
                hitX,
                hitY,
                hitZ);

        return bool == null ? false : bool;
    }

    public static boolean onItemUseFirst(Item item, ItemStack stack, EntityPlayer player, World world, int x, int y, int z,
                                  int side, float hitX, float hitY, float hitZ)
    {
        String meta = LuaItemMeta.getMetaClassForItem(item.getUnlocalizedName());
        Boolean bool = (Boolean) LuaHookManager.callMetatable(
                Luacraft.getInstance().getProxy().getLuaState(),
                "OnItemUseFirst",
                meta,
                new LuaItemStack(stack),
                new LuaEntityPlayer(player),
                new LuacraftWorld(world),
                x,
                y,
                z,
                side,
                hitX,
                hitY,
                hitZ);

        return bool == null ? false : bool;
    }

    public static boolean onBlockDestroyed(Item item, ItemStack stack, World world, Block block,
                                    int x, int y, int z, EntityLivingBase entity)
    {
        Boolean bool = null;

        return bool == null ? false : bool;
    }

    public static void onArmorTick(Item item, World world, EntityPlayer player, ItemStack stack)
    {

    }

    public static boolean onDroppedByPlayer(Item item, ItemStack stack, EntityPlayer player)
    {
        Boolean bool = null;

        return bool == null ? false : bool;
    }

    public static void onCreated(Item item, ItemStack stack, World world, EntityPlayer player)
    {

    }

    public static boolean onLeftClickEntity(Item item, ItemStack stack, EntityPlayer player, Entity entity)
    {
        Boolean bool = null;

        return bool == null ? false : bool;
    }

    public static boolean onBlockStartBreak(Item item, ItemStack itemstack, int X, int Y, int Z, EntityPlayer player)
    {
        Boolean bool = null;

        return bool == null ? false : bool;
    }

    public static ItemStack onItemRightClick(Item item, ItemStack stack, World world,
                                           EntityPlayer player)
    {
        ItemStack ret = null;

        return ret == null ? stack : null;
    }

    public static void onPlayerStoppedUsing(Item item, ItemStack stack, World world, EntityPlayer player, int timeLeft)
    {

    }

    public static boolean onEntitySwing(Item item, EntityLivingBase entityLiving, ItemStack stack)
    {
        Boolean bool = null;

        return bool == null ? false : bool;
    }

    public static boolean onEntityItemUpdate(Item item, EntityItem entityItem)
    {
        Boolean bool = null;

        return bool == null ? false : bool;
    }

    public static void onUpdate(Item item, ItemStack stack, World world, Entity entity, int itemSlot,
                           boolean isSelected)
    {

    }

    public static void onUsingTick(Item item, ItemStack stack, EntityPlayer player, int count)
    {

    }
}
