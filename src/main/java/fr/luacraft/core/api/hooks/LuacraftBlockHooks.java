package fr.luacraft.core.api.hooks;

import fr.luacraft.core.Luacraft;
import fr.luacraft.core.api.blocks.LuaIPlantable;
import fr.luacraft.core.api.entity.LuaEntityLivingBase;
import fr.luacraft.core.api.entity.LuaEntityPlayer;
import fr.luacraft.core.api.items.LuaItemStack;
import fr.luacraft.core.api.meta.LuaMetaUtil;
import fr.luacraft.core.api.meta.blocks.LuaBlockMeta;
import fr.luacraft.core.api.world.LuaExplosion;
import fr.luacraft.core.api.world.LuaIBlockAccess;
import fr.luacraft.core.api.world.LuaWorld;
import fr.luacraft.util.EnumUtil;
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
        String meta = LuaBlockMeta.getMetaClassForBlock(block.getUnlocalizedName());

        if(LuaMetaUtil.hasMetaMethod(Luacraft.getInstance().getProxy().getLuaState(), meta,
                "OnBlockActivated"))
        {
            return (Boolean) LuaHookManager.callMetatable(
                    Luacraft.getInstance().getProxy().getLuaState(),
                    "OnBlockActivated",
                    meta,
                    world,
                    x,
                    y,
                    z,
                    player,
                    p_149727_6_,
                    p_149727_7_,
                    p_149727_8_,
                    p_149727_9_)[0];
        }

        return false;
    }

    public static int onBlockPlaced(Block block, World world, int x, int y, int z,
                             int side, float hitX, float hitY, float hitZ, int metadata)
    {
       String meta = LuaBlockMeta.getMetaClassForBlock(block.getUnlocalizedName());
       LuaHookManager.callMetatable(
               Luacraft.getInstance().getProxy().getLuaState(),
               "OnBlockPlaced",
               meta,
               world,
               x,
               y,
               z,
               side,
               hitX,
               hitY,
               hitZ,
               metadata);
       return metadata;
    }

    public static void onBlockClicked(Block block, World world, int x, int y, int z, EntityPlayer player)
    {
        String meta = LuaBlockMeta.getMetaClassForBlock(block.getUnlocalizedName());
        LuaHookManager.callMetatable(
                Luacraft.getInstance().getProxy().getLuaState(),
                "OnBlockClicked",
                meta,
                world,
                x,
                y,
                z,
                player);
    }

    public static boolean onBlockEventReceived(Block block, World world, int x, int y, int z, int side, int metadata)
    {
        String meta = LuaBlockMeta.getMetaClassForBlock(block.getUnlocalizedName());

        if(LuaMetaUtil.hasMetaMethod(Luacraft.getInstance().getProxy().getLuaState(), meta,
                "OnBlockEventReceived"))
        {
            return (Boolean) LuaHookManager.callMetatable(
                    Luacraft.getInstance().getProxy().getLuaState(),
                    "OnBlockEventReceived",
                    meta,
                    world,
                    x,
                    y,
                    z,
                    side,
                    metadata)[0];
        }

        return false;
    }

    public static void onBlockAdded(Block block, World world, int x, int y, int z)
    {
        String meta = LuaBlockMeta.getMetaClassForBlock(block.getUnlocalizedName());

        LuaHookManager.callMetatable(
                Luacraft.getInstance().getProxy().getLuaState(),
                "OnBlockAdded",
                meta,
                world,
                x,
                y,
                z);
    }

    public static void onBlockDestroyedByExplosion(Block block, World world, int x, int y, int z,
                                                   Explosion explosion)
    {
        String meta = LuaBlockMeta.getMetaClassForBlock(block.getUnlocalizedName());

        LuaHookManager.callMetatable(
                Luacraft.getInstance().getProxy().getLuaState(),
                "OnBlockDestroyedByExplosion",
                meta,
                world,
                x,
                y,
                z,
                explosion);
    }

    public static void onBlockDestroyedByPlayer(Block block, World world, int x, int y, int z, int metadata)
    {
        String meta = LuaBlockMeta.getMetaClassForBlock(block.getUnlocalizedName());

        LuaHookManager.callMetatable(
                Luacraft.getInstance().getProxy().getLuaState(),
                "OnBlockDestroyedByPlayer",
                meta,
                world,
                x,
                y,
                z,
                metadata);
    }

    public static void onBlockExploded(Block block, World world, int x, int y, int z, Explosion explosion)
    {
        String meta = LuaBlockMeta.getMetaClassForBlock(block.getUnlocalizedName());

        LuaHookManager.callMetatable(
                Luacraft.getInstance().getProxy().getLuaState(),
                "OnBlockExploded",
                meta,
                world,
                x,
                y,
                z,
                explosion);
    }

    public static void onBlockHarvested(Block block, World world, int x, int y, int z, int metadata,
                                        EntityPlayer player)
    {
        String meta = LuaBlockMeta.getMetaClassForBlock(block.getUnlocalizedName());

        LuaHookManager.callMetatable(
                Luacraft.getInstance().getProxy().getLuaState(),
                "OnBlockHarvested",
                meta,
                world,
                x,
                y,
                z,
                metadata,
                player);
    }

    public static void onBlockPlacedBy(Block block, World world, int x, int y, int z,
                                       EntityLivingBase entity, ItemStack stack)
    {
        String meta = LuaBlockMeta.getMetaClassForBlock(block.getUnlocalizedName());

        LuaHookManager.callMetatable(
                Luacraft.getInstance().getProxy().getLuaState(),
                "OnBlockPlacedBy",
                meta,
                world,
                x,
                y,
                z,
                entity,
                stack);
    }

    public static void onBlockPreDestroy(Block block, World world, int x, int y, int z, int metadata)
    {
        String meta = LuaBlockMeta.getMetaClassForBlock(block.getUnlocalizedName());

        LuaHookManager.callMetatable(
                Luacraft.getInstance().getProxy().getLuaState(),
                "OnBlockPreDestroy",
                meta,
                world,
                x,
                y,
                z,
                metadata);
    }

    public static void onNeighborBlockChange(Block block, World world, int x, int y, int z, Block
                                             otherBlock)
    {
        String meta = LuaBlockMeta.getMetaClassForBlock(block.getUnlocalizedName());

        LuaHookManager.callMetatable(
                Luacraft.getInstance().getProxy().getLuaState(),
                "OnNeighborBlockChange",
                meta,
                world,
                x,
                y,
                z,
                otherBlock);
    }

    public static void onEntityCollidedWithBlock(Block block, World world, int x, int y, int z,
                                                 Entity entity)
    {
        String meta = LuaBlockMeta.getMetaClassForBlock(block.getUnlocalizedName());

        LuaHookManager.callMetatable(
                Luacraft.getInstance().getProxy().getLuaState(),
                "OnEntityCollidedWithBlock",
                meta,
                world,
                x,
                y,
                z,
                entity);
    }

    public static void onEntityWalking(Block block, World world, int x, int y, int z,
                                                 Entity entity)
    {
        String meta = LuaBlockMeta.getMetaClassForBlock(block.getUnlocalizedName());

        LuaHookManager.callMetatable(
                Luacraft.getInstance().getProxy().getLuaState(),
                "OnEntityWalking",
                meta,
                world,
                x,
                y,
                z,
                entity);
    }

    public static void onFallenUpon(Block block, World world, int x, int y, int z,
                                    Entity entity, float fallDistance)
    {
        String meta = LuaBlockMeta.getMetaClassForBlock(block.getUnlocalizedName());

        LuaHookManager.callMetatable(
                Luacraft.getInstance().getProxy().getLuaState(),
                "OnFallenUpon",
                meta,
                world,
                x,
                y,
                z,
                entity,
                fallDistance);
    }

    public static void onNeighborChange(Block block, IBlockAccess blockAccess,
                                        int x, int y, int z,
                                        int tileX, int tileY, int tileZ)
    {
        String meta = LuaBlockMeta.getMetaClassForBlock(block.getUnlocalizedName());

        LuaHookManager.callMetatable(
                Luacraft.getInstance().getProxy().getLuaState(),
                "OnNeighborChange",
                meta,
                blockAccess,
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
        String meta = LuaBlockMeta.getMetaClassForBlock(block.getUnlocalizedName());

        LuaHookManager.callMetatable(
                Luacraft.getInstance().getProxy().getLuaState(),
                "OnPlantGrow",
                meta,
                world,
                x,
                y,
                z,
                sourceX,
                sourceY,
                sourceZ);
    }

    public static void onPostBlockPlaced(Block block, World world, int x, int y, int z, int metadata)
    {
        String meta = LuaBlockMeta.getMetaClassForBlock(block.getUnlocalizedName());

        LuaHookManager.callMetatable(
                Luacraft.getInstance().getProxy().getLuaState(),
                "OnPostBlockPlaced",
                meta,
                world,
                x,
                y,
                z,
                metadata);
    }

    public static Boolean canConnectRedstone(Block block, IBlockAccess world, int x, int y, int z, int side)
    {
        String meta = LuaBlockMeta.getMetaClassForBlock(block.getUnlocalizedName());

        return (Boolean) LuaHookManager.callMetatable(
                Luacraft.getInstance().getProxy().getLuaState(),
                "OnPostBlockPlaced",
                meta,
                world,
                x,
                y,
                z,
                side)[0];
    }

    public static Boolean canProvidePower(Block block)
    {
        String meta = LuaBlockMeta.getMetaClassForBlock(block.getUnlocalizedName());

        return (Boolean) LuaHookManager.callMetatable(
                Luacraft.getInstance().getProxy().getLuaState(),
                "CanProvidePower",
                meta)[0];
    }

    public static Boolean canEntityDestroy(Block block, IBlockAccess world, int x, int y, int z, Entity entity)
    {
        String meta = LuaBlockMeta.getMetaClassForBlock(block.getUnlocalizedName());

        return (Boolean) LuaHookManager.callMetatable(
                Luacraft.getInstance().getProxy().getLuaState(),
                "CanEntityDestroy",
                meta,
                world,
                x,
                y,
                z,
                entity)[0];
    }

    public static Boolean canCreatureSpawn(Block block, EnumCreatureType type, IBlockAccess world,
                                           int x, int y, int z)
    {
        String meta = LuaBlockMeta.getMetaClassForBlock(block.getUnlocalizedName());

        return (Boolean) LuaHookManager.callMetatable(
                Luacraft.getInstance().getProxy().getLuaState(),
                "CanCreatureSpawn",
                meta,
                EnumUtil.getCreatureTypeAsInt(type),
                world,
                x,
                y,
                z)[0];
    }

    public static Boolean canBlockStay(Block block, World world, int x, int y, int z)
    {
        String meta = LuaBlockMeta.getMetaClassForBlock(block.getUnlocalizedName());

        return (Boolean) LuaHookManager.callMetatable(
                Luacraft.getInstance().getProxy().getLuaState(),
                "CanBlockStay",
                meta,
                world,
                x,
                y,
                z)[0];
    }

    public static Boolean canPlaceTorchOnTop(Block block, World world, int x, int y, int z)
    {
        String meta = LuaBlockMeta.getMetaClassForBlock(block.getUnlocalizedName());

        return (Boolean) LuaHookManager.callMetatable(
                Luacraft.getInstance().getProxy().getLuaState(),
                "CanPlaceTorchOnTop",
                meta,
                world,
                x,
                y,
                z)[0];
    }

    public static Boolean canDropFromExplosion(Block block, Explosion explosion)
    {
        String meta = LuaBlockMeta.getMetaClassForBlock(block.getUnlocalizedName());

        return (Boolean) LuaHookManager.callMetatable(
                Luacraft.getInstance().getProxy().getLuaState(),
                "CanDropFromExplosion",
                meta,
                explosion)[0];
    }

    public static Boolean canHarvestBlock(Block block, EntityPlayer player, int metadata)
    {
        String meta = LuaBlockMeta.getMetaClassForBlock(block.getUnlocalizedName());

        return (Boolean) LuaHookManager.callMetatable(
                Luacraft.getInstance().getProxy().getLuaState(),
                "CanHarvestBlock",
                meta,
                player,
                metadata)[0];
    }

    public static Boolean canPlaceBlockAt(Block block, World world, int x, int y, int z)
    {
        String meta = LuaBlockMeta.getMetaClassForBlock(block.getUnlocalizedName());

        return (Boolean) LuaHookManager.callMetatable(
                Luacraft.getInstance().getProxy().getLuaState(),
                "CanPlaceBlockAt",
                meta,
                world,
                x,
                y,
                z)[0];
    }

    public static Boolean canBeReplacedByLeaves(Block block, IBlockAccess world, int x, int y, int z)
    {
        String meta = LuaBlockMeta.getMetaClassForBlock(block.getUnlocalizedName());

        return (Boolean) LuaHookManager.callMetatable(
                Luacraft.getInstance().getProxy().getLuaState(),
                "CanBeReplacedByLeaves",
                meta,
                world,
                x,
                y,
                z)[0];
    }

    public static Boolean canCollideCheck(Block block, int metadata, boolean boat)
    {
        String meta = LuaBlockMeta.getMetaClassForBlock(block.getUnlocalizedName());

        return (Boolean) LuaHookManager.callMetatable(
                Luacraft.getInstance().getProxy().getLuaState(),
                "CanCollideCheck",
                meta,
                metadata,
                boat)[0];
    }

    public static Boolean canPlaceBlockOnSide(Block block, World world, int x, int y, int z, int side)
    {
        String meta = LuaBlockMeta.getMetaClassForBlock(block.getUnlocalizedName());

        return (Boolean) LuaHookManager.callMetatable(
                Luacraft.getInstance().getProxy().getLuaState(),
                "CanPlaceBlockOnSide",
                meta,
                world,
                x,
                y,
                z,
                side)[0];
    }

    public static Boolean canRenderInPass(Block block, int pass)
    {
        String meta = LuaBlockMeta.getMetaClassForBlock(block.getUnlocalizedName());

        return (Boolean) LuaHookManager.callMetatable(
                Luacraft.getInstance().getProxy().getLuaState(),
                "CanRenderInPass",
                meta,
                pass)[0];
    }

    public static Boolean canSustainLeaves(Block block, IBlockAccess world, int x, int y, int z)
    {
        String meta = LuaBlockMeta.getMetaClassForBlock(block.getUnlocalizedName());

        return (Boolean) LuaHookManager.callMetatable(
                Luacraft.getInstance().getProxy().getLuaState(),
                "CanSustainLeaves",
                meta,
                world,
                x,
                y,
                z)[0];
    }

    public static Boolean canSustainPlant(Block block, IBlockAccess world, int x, int y, int z,
                                          ForgeDirection direction, IPlantable plantable)
    {
        String meta = LuaBlockMeta.getMetaClassForBlock(block.getUnlocalizedName());

        return (Boolean) LuaHookManager.callMetatable(
                Luacraft.getInstance().getProxy().getLuaState(),
                "CanSustainPlant",
                meta,
                world,
                x,
                y,
                z,
                EnumUtil.getForgeDirectionAsInt(direction),
                plantable)[0];
    }

    public static Boolean canReplace(Block block, World world, int x, int y, int z, int side,
                                     ItemStack stack)
    {
        String meta = LuaBlockMeta.getMetaClassForBlock(block.getUnlocalizedName());

        return (Boolean) LuaHookManager.callMetatable(
                Luacraft.getInstance().getProxy().getLuaState(),
                "CanReplace",
                meta,
                world,
                x,
                y,
                z,
                side,
                stack)[0];
    }

    public static Boolean canSilkHarvest(Block block, World world, EntityPlayer player, int x, int y, int z,
                                  int metadata)
    {
        String meta = LuaBlockMeta.getMetaClassForBlock(block.getUnlocalizedName());

        return (Boolean) LuaHookManager.callMetatable(
                Luacraft.getInstance().getProxy().getLuaState(),
                "canSilkHarvest",
                meta,
                world,
                player,
                x,
                y,
                z,
                metadata)[0];
    }
}