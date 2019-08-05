package fr.luacraft.core.api.hooks;

import fr.luacraft.core.api.blocks.LuaBlock;
import fr.luacraft.core.api.blocks.LuaIPlantable;
import fr.luacraft.core.api.entity.LuaEntity;
import fr.luacraft.core.api.entity.LuaEntityLivingBase;
import fr.luacraft.core.api.entity.LuaEntityPlayer;
import fr.luacraft.core.api.items.LuaItemStack;
import fr.luacraft.core.api.world.LuaExplosion;
import fr.luacraft.core.api.world.LuaIBlockAccess;
import fr.luacraft.core.api.world.LuaWorld;
import fr.luacraft.util.LuaUtil;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Shared block hooks
 * @author Zino
 */
public class LuacraftBlockHooks
{
    public static boolean onBlockActivated(Block block, World world, int x, int y, int z, EntityPlayer player,
                                    int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
    {
        Boolean bool = LuaHookManager.call(
                Boolean.class,
                block,
                "OnBlockActivated",
                new LuaWorld(world),
                x,
                y,
                z,
                new LuaEntityPlayer(player),
                p_149727_6_,
                p_149727_7_,
                p_149727_8_,
                p_149727_9_);

        return bool == null ? false : bool;
    }

    public static int onBlockPlaced(Block block, World world, int x, int y, int z,
                             int side, float hitX, float hitY, float hitZ, int metadata)
    {
        Integer md = LuaHookManager.call(
                Integer.class,
                block,
                "OnBlockPlaced",
                new LuaWorld(world),
                x,
                y,
                z,
                side,
                hitX,
                hitY,
                hitZ,
                metadata);

        return md == null ? metadata : md;
    }

    public static void onBlockClicked(Block block, World world, int x, int y, int z, EntityPlayer player)
    {
        LuaHookManager.call(
                block,
                "OnBlockClicked",
                new LuaWorld(world),
                x,
                y,
                z,
                new LuaEntityPlayer(player));
    }

    public static boolean onBlockEventReceived(Block block, World world, int x, int y, int z, int side, int metadata)
    {
        Boolean bool = LuaHookManager.call(
                Boolean.class,
                block,
                "OnBlockEventReceieved",
                new LuaWorld(world),
                x,
                y,
                z,
                side,
                metadata
        );

        return bool == null ? false : bool;
    }

    public static void onBlockAdded(Block block, World world, int x, int y, int z)
    {
        LuaHookManager.call(
                block,
                "OnBlockAdded",
                new LuaWorld(world),
                x,
                y,
                z);
    }

    public static void onBlockDestroyedByExplosion(Block block, World world, int x, int y, int z,
                                                   Explosion explosion)
    {
        LuaHookManager.call(
                block,
                "OnBlockDestroyedByExplosion",
                new LuaWorld(world),
                x,
                y,
                z,
                new LuaExplosion(explosion));
    }

    public static void onBlockDestroyedByPlayer(Block block, World world, int x, int y, int z, int metadata)
    {
        LuaHookManager.call(
                block,
                "OnBlockDestroyedByPlayer",
                new LuaWorld(world),
                x,
                y,
                z,
                metadata);
    }

    public static void onBlockExploded(Block block, World world, int x, int y, int z, Explosion explosion)
    {
        LuaHookManager.call(
                block,
                "OnBlockExploded",
                new LuaWorld(world),
                x,
                y,
                z,
                new LuaExplosion(explosion));
    }

    public static void onBlockHarvested(Block block, World world, int x, int y, int z, int metadata,
                                        EntityPlayer player)
    {
        LuaHookManager.call(
                block,
                "OnBlockHarvested",
                new LuaWorld(world),
                x,
                y,
                z,
                metadata,
                new LuaEntityPlayer(player));
    }

    public static void onBlockPlacedBy(Block block, World world, int x, int y, int z,
                                       EntityLivingBase entity, ItemStack stack)
    {
        LuaHookManager.call(
                block,
                "OnBlockPlacedBy",
                new LuaWorld(world),
                x,
                y,
                z,
                new LuaEntityLivingBase(entity),
                new LuaItemStack(stack));
    }

    public static void onBlockPreDestroy(Block block, World world, int x, int y, int z, int metadata)
    {
        LuaHookManager.call(
                block,
                "OnBlockPreDestroy",
                new LuaWorld(world),
                x,
                y,
                z,
                metadata);
    }

    public static void onNeighborBlockChange(Block block, World world, int x, int y, int z, Block
                                             otherBlock)
    {
        LuaHookManager.call(
                block,
                "OnNeighborBlockChange",
                new LuaWorld(world),
                x,
                y,
                z,
                new LuaBlock(otherBlock));
    }

    public static void onEntityCollidedWithBlock(Block block, World world, int x, int y, int z,
                                                 Entity entity)
    {
        LuaHookManager.call(
                block,
                "OnEntityCollidedWithBlock",
                new LuaWorld(world),
                x,
                y,
                z,
                new LuaEntity(entity));
    }

    public static void onEntityWalking(Block block, World world, int x, int y, int z,
                                                 Entity entity)
    {
        LuaHookManager.call(
                block,
                "OnEntityWalking",
                new LuaWorld(world),
                x,
                y,
                z,
                new LuaEntity(entity));
    }

    public static void onFallenUpon(Block block, World world, int x, int y, int z,
                                    Entity entity, float fallDistance)
    {
        LuaHookManager.call(
                block,
                "OnFallenUpon",
                new LuaWorld(world),
                x,
                y,
                z,
                new LuaEntity(entity),
                fallDistance);
    }

    public static void onNeighborChange(Block block, IBlockAccess blockAccess,
                                        int x, int y, int z,
                                        int tileX, int tileY, int tileZ)
    {
        LuaHookManager.call(
                block,
                "OnNeighborChange",
                new LuaIBlockAccess(blockAccess),
                x,
                y,
                z,
                tileX,
                tileY,
                tileZ);
    }

    public static void onPlantGrow(Block block, World world, int x, int y, int z,
                                   int sourceX, int sourceY, int sourceZ)
    {
        LuaHookManager.call(
                block,
                "OnPlantGrow",
                new LuaWorld(world),
                x,
                y,
                z,
                sourceX,
                sourceY,
                sourceZ);
    }

    public static void onPostBlockPlaced(Block block, World world, int x, int y, int z, int metadata)
    {
        LuaHookManager.call(
                block,
                "OnPostBlockPlaced",
                new LuaWorld(world),
                x,
                y,
                z,
                metadata);
    }

    public static Boolean canConnectRedstone(Block block, IBlockAccess world, int x, int y, int z, int side)
    {
        return LuaHookManager.call(
                Boolean.class,
                block,
                "CanConnectRedstone",
                new LuaIBlockAccess(world),
                x,
                y,
                z,
                side);
    }

    public static Boolean canProvidePower(Block block)
    {
        return LuaHookManager.call(
                Boolean.class,
                block,
                "CanProvidePower");
    }

    public static Boolean canEntityDestroy(Block block, IBlockAccess world, int x, int y, int z, Entity entity)
    {
        return LuaHookManager.call(
                Boolean.class,
                block,
                "CanEntityDestroy",
                new LuaIBlockAccess(world),
                x,
                y,
                z,
                entity);
    }

    public static Boolean canCreatureSpawn(Block block, EnumCreatureType type, IBlockAccess world,
                                           int x, int y, int z)
    {
        return LuaHookManager.call(
                Boolean.class,
                block,
                "CanCreatureSpawn",
                LuaUtil.getCreatureTypeId(type),
                new LuaIBlockAccess(world),
                x,
                y,
                z);
    }

    public static Boolean canBlockStay(Block block, World world, int x, int y, int z)
    {
        return LuaHookManager.call(
                Boolean.class,
                block,
                "CanBlockStay",
                new LuaWorld(world),
                x,
                y,
                z);
    }

    public static Boolean canPlaceTorchOnTop(Block block, World world, int x, int y, int z)
    {
        return LuaHookManager.call(
                Boolean.class,
                block,
                "CanPlaceTorchOnTop",
                new LuaWorld(world),
                x,
                y,
                z);
    }

    public static Boolean canDropFromExplosion(Block block, Explosion explosion)
    {
        return LuaHookManager.call(
                Boolean.class,
                block,
                "CanDropFromExplosion",
                new LuaExplosion(explosion));
    }

    public static Boolean canHarvestBlock(Block block, EntityPlayer player, int meta)
    {
        return LuaHookManager.call(
                Boolean.class,
                block,
                "CanHarvestBlock",
                new LuaEntityPlayer(player),
                meta);
    }

    public static Boolean canPlaceBlockAt(Block block, World world, int x, int y, int z)
    {
        return LuaHookManager.call(
                Boolean.class,
                block,
                "CanPlaceBlockAt",
                new LuaWorld(world),
                x,
                y,
                z);
    }

    public static Boolean canBeReplacedByLeaves(Block block, IBlockAccess world, int x, int y, int z)
    {
        return LuaHookManager.call(
                Boolean.class,
                block,
                "CanBeReplacedByLeaves",
                new LuaIBlockAccess(world),
                x,
                y,
                z);
    }

    public static Boolean canCollideCheck(Block block, int meta, boolean boat)
    {
        return LuaHookManager.call(
                Boolean.class,
                block,
                "CanCollideCheck",
                meta,
                boat);
    }

    public static Boolean canPlaceBlockOnSide(Block block, World world, int x, int y, int z, int side)
    {
        return LuaHookManager.call(
                Boolean.class,
                block,
                "CanPlaceBlockOnSide",
                new LuaWorld(world),
                x,
                y,
                z,
                side);
    }

    public static Boolean canRenderInPass(Block block, int pass)
    {
        return LuaHookManager.call(
                Boolean.class,
                block,
                "CanRenderInPass",
                pass);
    }

    public static Boolean canSustainLeaves(Block block, IBlockAccess world, int x, int y, int z)
    {
        return LuaHookManager.call(
                Boolean.class,
                block,
                "CanSustainLeaves",
                new LuaIBlockAccess(world),
                x,
                y,
                z);
    }

    public static Boolean canSustainPlant(Block block, IBlockAccess world, int x, int y, int z,
                                          ForgeDirection direction, IPlantable plantable)
    {
        return LuaHookManager.call(
                Boolean.class,
                block,
                "CanSustainPlant",
                new LuaIBlockAccess(world),
                x,
                y,
                z,
                LuaUtil.getForgeDirectionAsInt(direction),
                new LuaIPlantable(plantable));
    }

    public static Boolean canReplace(Block block, World world, int x, int y, int z, int side,
                                     ItemStack stack)
    {
        return LuaHookManager.call(
                Boolean.class,
                block,
                "CanReplace",
                new LuaWorld(world),
                x,
                y,
                z,
                side,
                new LuaItemStack(stack));
    }

    public static Boolean canSilkHarvest(Block block, World world, EntityPlayer player, int x, int y, int z,
                                  int metadata)
    {
        return LuaHookManager.call(
                Boolean.class,
                block,
                "CanSilkHarvest",
                new LuaWorld(world),
                new LuaEntityPlayer(player),
                x,
                y,
                z,
                metadata);
    }
}