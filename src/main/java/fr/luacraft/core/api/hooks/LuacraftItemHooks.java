package fr.luacraft.core.api.hooks;

import fr.luacraft.core.api.blocks.LuaBlock;
import fr.luacraft.core.api.entity.LuaEntity;
import fr.luacraft.core.api.entity.LuaEntityItem;
import fr.luacraft.core.api.entity.LuaEntityLivingBase;
import fr.luacraft.core.api.entity.LuaEntityPlayer;
import fr.luacraft.core.api.items.LuaItemStack;
import fr.luacraft.core.api.world.LuaWorld;
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
 * TODO: I wanted to use a basic interface with default functions instead of this class but this is not supported in Java 6. Maybe in the future versions if we abandon support to all versions that uses Java 6/7, we can transition an Interface.
 * @quthor Zino
 */
public class LuacraftItemHooks
{
    public static boolean onItemUse(Item item, ItemStack stack, EntityPlayer player, World world, int x,
                                    int y, int z, int hand, float hitX, float hitY, float hitZ)
    {
        Boolean bool = LuaHookManager.call(
                Boolean.class,
                item,
                "OnItemUse",
                new LuaItemStack(stack),
                new LuaEntityPlayer(player),
                new LuaWorld(world),
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
        Boolean bool = LuaHookManager.call(
                Boolean.class,
                item,
                "OnItemUseFirst",
                new LuaItemStack(stack),
                new LuaEntityPlayer(player),
                new LuaWorld(world),
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
        Boolean bool = LuaHookManager.call(
                Boolean.class,
                item,
                "OnBlockDestroyed",
                new LuaBlock(block),
                x,
                y,
                z,
                new LuaEntityLivingBase(entity));

        return bool == null ? false : bool;
    }

    public static void onArmorTick(Item item, World world, EntityPlayer player, ItemStack stack)
    {
        LuaHookManager.call(
                item,
                "OnArmorTick",
                new LuaWorld(world),
                new LuaEntityPlayer(player),
                new LuaItemStack(stack));
    }

    public static boolean onDroppedByPlayer(Item item, ItemStack stack, EntityPlayer player)
    {
        Boolean bool = LuaHookManager.call(
                Boolean.class,
                item,
                "OnDroppedByPlayer",
                new LuaItemStack(stack),
                new LuaEntityPlayer(player));

        return bool == null ? false : bool;
    }

    public static void onCreated(Item item, ItemStack stack, World world, EntityPlayer player)
    {
        LuaHookManager.call(
                item,
                "OnCreated",
                new LuaItemStack(stack),
                new LuaWorld(world),
                new LuaEntityPlayer(player));
    }

    public static boolean onLeftClickEntity(Item item, ItemStack stack, EntityPlayer player, Entity entity)
    {
        Boolean bool = LuaHookManager.call(
                Boolean.class,
                item,
                "OnLeftClickEntity",
                new LuaItemStack(stack),
                new LuaEntityPlayer(player),
                new LuaEntity(entity));

        return bool == null ? false : bool;
    }

    public static boolean onBlockStartBreak(Item item, ItemStack itemstack, int X, int Y, int Z, EntityPlayer player)
    {
        Boolean bool = LuaHookManager.call(
                Boolean.class,
                item,
                "OnBlockStartBreak",
                new LuaItemStack(itemstack),
                X,
                Y,
                Z,
                new LuaEntityPlayer(player));

        return bool == null ? false : bool;
    }

    public static ItemStack onItemRightClick(Item item, ItemStack stack, World world,
                                           EntityPlayer player)
    {
        LuaItemStack ret = LuaHookManager.call(
                LuaItemStack.class,
                item,
                "OnItemRightClick",
                new LuaItemStack(stack),
                new LuaWorld(world),
                new LuaEntityPlayer(player));

        return ret == null ? stack : ret.getItemStack();
    }

    public static void onPlayerStoppedUsing(Item item, ItemStack stack, World world, EntityPlayer player, int timeLeft)
    {
        LuaHookManager.call(
                item,
                "OnPlayerStoppedUsing",
                new LuaItemStack(stack),
                new LuaWorld(world),
                new LuaEntityPlayer(player),
                timeLeft);
    }

    public static boolean onEntitySwing(Item item, EntityLivingBase entityLiving, ItemStack stack)
    {
        Boolean bool = LuaHookManager.call(
                Boolean.class,
                item,
                "OnEntitySwing",
                new LuaEntityLivingBase(entityLiving),
                new LuaItemStack(stack));

        return bool == null ? false : bool;
    }

    public static boolean onEntityItemUpdate(Item item, EntityItem entityItem)
    {
        Boolean bool = LuaHookManager.call(
                Boolean.class,
                item,
                "OnEntityItemUpdate",
                new LuaEntityItem(entityItem));

        return bool == null ? false : bool;
    }

    public static void onUpdate(Item item, ItemStack stack, World world, Entity entity, int itemSlot,
                           boolean isSelected)
    {
        LuaHookManager.call(
                item,
                "OnUpdate",
                new LuaItemStack(stack),
                new LuaWorld(world),
                new LuaEntity(entity),
                itemSlot,
                isSelected);
    }

    public static void onUsingTick(Item item, ItemStack stack, EntityPlayer player, int count)
    {
        LuaHookManager.call(
                item,
                "OnUsingTick",
                new LuaItemStack(stack),
                new LuaEntityPlayer(player),
                count);
    }
}
