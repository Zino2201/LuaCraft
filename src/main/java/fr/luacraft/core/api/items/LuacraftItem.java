package fr.luacraft.core.api.items;

import fr.luacraft.core.Luacraft;
import fr.luacraft.core.api.blocks.LuaBlock;
import fr.luacraft.core.api.entity.LuaEntity;
import fr.luacraft.core.api.entity.LuaEntityItem;
import fr.luacraft.core.api.entity.LuaEntityLivingBase;
import fr.luacraft.core.api.entity.LuaEntityPlayer;
import fr.luacraft.core.api.hooks.LuaHookManager;
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
 * A item compatible with Luacraft hooks
 * @author Zino
 */
public class LuacraftItem extends Item
{
    private boolean isMap;
    private boolean isDamageable;
    private boolean isRepairable;

    public LuacraftItem(String id)
    {
        this.setUnlocalizedName(id);
        this.setTextureName(Luacraft.getInstance().getProxy().getCurrentMod().getModId() + ":" + id);
        this.setFull3D();

        this.isDamageable = true;
        this.isMap = false;
        this.isRepairable = true;
    }

    @Override
    public boolean isMap()
    {
        return isMap;
    }

    @Override
    public boolean isDamageable()
    {
        return isDamageable;
    }

    @Override
    public boolean isRepairable()
    {
        return isRepairable;
    }

    public void setIsMap(boolean map)
    {
        isMap = map;
    }

    public void setDamageable(boolean dmgable)
    {
        isDamageable = dmgable;
    }

    public void setRepairable(boolean repairable)
    {
        isRepairable = repairable;
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z,
                             int hand, float hitX, float hitY, float hitZ)
    {
        if(!world.isRemote)
        {
            return LuaHookManager.callReturn(
                    this, "OnItemUse",
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
        }

        return super.onItemUse(stack, player, world, x, y, z, hand, hitX, hitY, hitZ);
    }

    @Override
    public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z,
                                  int side, float hitX, float hitY, float hitZ)
    {
        if(!world.isRemote)
        {
            return LuaHookManager.callReturn(
                    this, "OnItemUseFirst",
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
        }

        return super.onItemUseFirst(stack, player, world, x, y, z, side, hitX, hitY, hitZ);
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World world, Block block,
                                    int x, int y, int z, EntityLivingBase entity)
    {
        if(!world.isRemote)
        {
            return LuaHookManager.callReturn(
                    this, "OnBlockDestroyed",
                    new LuaBlock(block),
                    x,
                    y,
                    z,
                    new LuaEntityLivingBase(entity));
        }

        return super.onBlockDestroyed(stack, world, block, x, y, z, entity);
    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack)
    {
        if(!world.isRemote)
        {
            LuaHookManager.call(this, "OnArmorTick",
                    new LuaWorld(world),
                    new LuaEntityPlayer(player),
                    new LuaItemStack(itemStack));
        }

        super.onArmorTick(world, player, itemStack);
    }

    @Override
    public boolean onDroppedByPlayer(ItemStack item, EntityPlayer player)
    {
        LuaHookManager.call(this, "OnDroppedByPlayer",
                new LuaItemStack(item),
                new LuaEntityPlayer(player));

        return super.onDroppedByPlayer(item, player);
    }

    @Override
    public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player)
    {
        if(!world.isRemote)
        {
            return LuaHookManager.callReturn(
                    LuaItemStack.class,
                    this , "OnEaten",
                    new LuaWorld(world),
                    new LuaEntityPlayer(player)).getItemStack();
        }

        return super.onEaten(stack, world, player);
    }

    @Override
    public void onCreated(ItemStack stack, World world, EntityPlayer player)
    {
        if(!world.isRemote)
        {
            LuaHookManager.call(this, "OnCreated",
                    new LuaItemStack(stack),
                    new LuaWorld(world),
                    new LuaEntityPlayer(player));
        }

        super.onCreated(stack, world, player);
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
    {
        return LuaHookManager.callReturn(this, "OnLeftClickEntity",
                new LuaItemStack(stack),
                new LuaEntityPlayer(player),
                new LuaEntity(entity));
    }

    @Override
    public boolean onBlockStartBreak(ItemStack itemstack, int X, int Y, int Z, EntityPlayer player)
    {
        return LuaHookManager.callReturn(this, "OnBlockStartBreak",
                new LuaItemStack(itemstack),
                X,
                Y,
                Z,
                new LuaEntityPlayer(player));
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
        if(!world.isRemote)
        {
            return LuaHookManager.callReturn(ItemStack.class, this, "OnItemRightClick",
                    new LuaItemStack(stack),
                    new LuaWorld(world),
                    new LuaEntityPlayer(player));
        }

        return super.onItemRightClick(stack, world, player);
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World world, EntityPlayer player, int timeLeft)
    {
        if(!world.isRemote)
        {
            LuaHookManager.call(this, "OnPlayerStoppedUsing",
                    new LuaItemStack(stack),
                    new LuaWorld(world),
                    new LuaEntityPlayer(player),
                    timeLeft);
        }

        super.onPlayerStoppedUsing(stack, world, player, timeLeft);
    }

    @Override
    public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack)
    {
        return LuaHookManager.callReturn(this, "OnEntitySwing",
                new LuaEntityLivingBase(entityLiving),
                new LuaItemStack(stack));
    }

    @Override
    public boolean onEntityItemUpdate(EntityItem entityItem)
    {
        return LuaHookManager.callReturn(this, "OnEntityItemUpdate",
                new LuaEntityItem(entityItem));
    }

    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot,
                         boolean isSelected)
    {
        if(!world.isRemote)
        {
            LuaHookManager.call(this, "OnUpdate",
                    new LuaItemStack(stack),
                    new LuaWorld(world),
                    new LuaEntity(entity),
                    itemSlot,
                    isSelected);
        }

        super.onUpdate(stack, world, entity, itemSlot, isSelected);
    }

    @Override
    public void onUsingTick(ItemStack stack, EntityPlayer player, int count)
    {
        LuaHookManager.call(this, "OnUsingTick",
                new LuaItemStack(stack),
                new LuaEntityPlayer(player),
                count);

        super.onUsingTick(stack, player, count);
    }
}
