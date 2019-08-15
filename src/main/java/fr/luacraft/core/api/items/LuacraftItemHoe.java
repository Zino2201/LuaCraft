package fr.luacraft.core.api.items;

import fr.luacraft.core.Luacraft;
import fr.luacraft.core.api.hooks.LuacraftItemHooks;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * Luacraft hoe
 * @author Zino
 */
public class LuacraftItemHoe extends ItemHoe
{
    public LuacraftItemHoe(String id, String material)
    {
        super(ToolMaterial.valueOf(material));

        this.setUnlocalizedName(id);
        this.setTextureName(Luacraft.getInstance().getModLoader().getCurrentMod().getModId() + ":" + id);
        this.setFull3D();
    }

    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z,
                             int hand, float hitX, float hitY, float hitZ)
    {
        super.onItemUse(stack, player, world, x, y, z, hand, hitX, hitY, hitZ);

        return LuacraftItemHooks.onItemUse(this, stack, player, world,
                x, y, z, hand, hitX, hitY, hitZ);
    }

    @Override
    public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z,
                                  int side, float hitX, float hitY, float hitZ)
    {
        super.onItemUseFirst(stack, player, world, x, y, z, side, hitX, hitY, hitZ);

        return LuacraftItemHooks.onItemUseFirst(this, stack, player, world, x, y, z, side,
                hitX, hitY, hitZ);
    }

    @Override
    public boolean onBlockDestroyed(ItemStack stack, World world, Block block,
                                    int x, int y, int z, EntityLivingBase entity)
    {
        super.onBlockDestroyed(stack, world, block, x, y, z, entity);

        return LuacraftItemHooks.onBlockDestroyed(this, stack, world, block, x, y, z, entity);
    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack)
    {
        super.onArmorTick(world, player, itemStack);

        LuacraftItemHooks.onArmorTick(this, world, player, itemStack);
    }

    @Override
    public boolean onDroppedByPlayer(ItemStack item, EntityPlayer player)
    {
        super.onDroppedByPlayer(item, player);

        return LuacraftItemHooks.onDroppedByPlayer(this, item, player);
    }

    @Override
    public void onCreated(ItemStack stack, World world, EntityPlayer player)
    {
        super.onCreated(stack, world, player);

        LuacraftItemHooks.onCreated(this, stack, world, player);
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
    {
        super.onLeftClickEntity(stack, player, entity);

        return LuacraftItemHooks.onLeftClickEntity(this, stack, player, entity);
    }

    @Override
    public boolean onBlockStartBreak(ItemStack itemstack, int X, int Y, int Z, EntityPlayer player)
    {
        super.onBlockStartBreak(itemstack, X, Y, Z, player);

        return LuacraftItemHooks.onBlockStartBreak(this, itemstack, X, Y, Z, player);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
        super.onItemRightClick(stack, world, player);

        return LuacraftItemHooks.onItemRightClick(this, stack, world, player);
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World world, EntityPlayer player, int timeLeft)
    {
        super.onPlayerStoppedUsing(stack, world, player, timeLeft);

        LuacraftItemHooks.onPlayerStoppedUsing(this, stack, world, player, timeLeft);
    }

    @Override
    public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack stack)
    {
        super.onEntitySwing(entityLiving, stack);

        return LuacraftItemHooks.onEntitySwing(this, entityLiving, stack);
    }

    @Override
    public boolean onEntityItemUpdate(EntityItem entityItem)
    {
        super.onEntityItemUpdate(entityItem);

        return LuacraftItemHooks.onEntityItemUpdate(this, entityItem);
    }

    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot,
                         boolean isSelected)
    {
        super.onUpdate(stack, world, entity, itemSlot, isSelected);

        LuacraftItemHooks.onUpdate(this, stack, world, entity, itemSlot, isSelected);
    }

    @Override
    public void onUsingTick(ItemStack stack, EntityPlayer player, int count)
    {
        super.onUsingTick(stack, player, count);

        LuacraftItemHooks.onUsingTick(this, stack, player, count);
    }
}
