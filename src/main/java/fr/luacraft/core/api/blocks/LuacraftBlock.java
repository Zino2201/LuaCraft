package fr.luacraft.core.api.blocks;

import fr.luacraft.core.Luacraft;
import fr.luacraft.core.api.entity.LuacraftTileEntity;
import fr.luacraft.core.api.hooks.LuacraftBlockHooks;
import fr.luacraft.core.api.meta.LuaMetaHook;
import fr.luacraft.core.api.util.LuaClass;
import fr.luacraft.util.EnumUtil;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * A Luacraft block compatible with Luacraft hooks
 * @author Zino
 */
public class LuacraftBlock extends Block
{
    @Deprecated
    private LuaClass tileEntityClass;

    public LuacraftBlock(String name, int material, LuaClass tileEntityClass)
    {
        super(EnumUtil.getMaterialFromInt(material));

        this.tileEntityClass = tileEntityClass;

        this.setBlockName(name);
        this.setBlockTextureName(Luacraft.getInstance().getModLoader().getCurrentMod().getModId() + ":" + name);
    }

    @Override
    @LuaMetaHook
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player,
                                    int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
    {
        super.onBlockActivated(world, x, y, z, player, p_149727_6_, p_149727_7_, p_149727_8_, p_149727_9_);

        return LuacraftBlockHooks.onBlockActivated(
                this,
                world,
                x,
                y,
                z,
                player,
                p_149727_6_,
                p_149727_7_,
                p_149727_8_,
                p_149727_9_);
    }

    @Override
    @LuaMetaHook
    public int onBlockPlaced(World world, int x, int y, int z,
                             int side, float hitX, float hitY, float hitZ, int metadata)
    {
        super.onBlockPlaced(world, x, y, z, side, hitX, hitY, hitZ, metadata);

        return LuacraftBlockHooks.onBlockPlaced(
                this,
                world,
                x,
                y,
                z,
                side,
                hitX,
                hitY,
                hitZ,
                metadata);
    }

    @Override
    @LuaMetaHook
    public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player)
    {
        super.onBlockClicked(world, x, y, z, player);

        LuacraftBlockHooks.onBlockClicked(this, world, x, y, z, player);
    }

    @Override
    @LuaMetaHook
    public boolean onBlockEventReceived(World world, int x, int y, int z, int side, int metadata)
    {
        super.onBlockEventReceived(world, x, y, z, side, metadata);

        return LuacraftBlockHooks.onBlockEventReceived(this, world, x, y, z, side, metadata);
    }

    @Override
    @LuaMetaHook
    public void onBlockAdded(World world, int x, int y, int z)
    {
        super.onBlockAdded(world, x, y, z);

        LuacraftBlockHooks.onBlockAdded(this, world, x, y, z);
    }

    @Override
    @LuaMetaHook
    public void onBlockDestroyedByExplosion(World world, int x, int y, int z, Explosion explosion)
    {
        super.onBlockDestroyedByExplosion(world, x, y, z, explosion);

        LuacraftBlockHooks.onBlockDestroyedByExplosion(this, world, x, y, z, explosion);
    }

    @Override
    @LuaMetaHook
    public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int metadata)
    {
        super.onBlockDestroyedByPlayer(world, x, y, z, metadata);

        LuacraftBlockHooks.onBlockDestroyedByPlayer(this, world, x, y, z, metadata);
    }

    @Override
    @LuaMetaHook
    public void onBlockExploded(World world, int x, int y, int z, Explosion explosion)
    {
        super.onBlockExploded(world, x, y, z, explosion);

        LuacraftBlockHooks.onBlockExploded(this, world, x, y, z, explosion);
    }

    @Override
    @LuaMetaHook
    public void onBlockHarvested(World world, int x, int y, int z, int metadata, EntityPlayer player)
    {
        super.onBlockHarvested(world, x, y, z, metadata, player);

        LuacraftBlockHooks.onBlockHarvested(this, world, x, y, z, metadata, player);
    }

    @Override
    @LuaMetaHook
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack)
    {
        super.onBlockPlacedBy(world, x, y, z, entity, stack);

        LuacraftBlockHooks.onBlockPlacedBy(this, world, x, y, z, entity, stack);
    }

    @Override
    @LuaMetaHook
    public void onBlockPreDestroy(World world, int x, int y, int z, int metadata)
    {
        super.onBlockPreDestroy(world, x, y, z, metadata);

        LuacraftBlockHooks.onBlockPreDestroy(this, world, x, y, z, metadata);
    }

    @Override
    @LuaMetaHook
    public void onNeighborBlockChange(World world, int x, int y, int z, Block otherBlock)
    {
        super.onNeighborBlockChange(world, x, y, z, otherBlock);

        LuacraftBlockHooks.onNeighborBlockChange(this, world, x, y, z, otherBlock);
    }

    @Override
    @LuaMetaHook
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
    {
        super.onEntityCollidedWithBlock(world, x, y, z, entity);

        LuacraftBlockHooks.onEntityCollidedWithBlock(this, world, x, y, z, entity);
    }

    @Override
    @LuaMetaHook
    public void onEntityWalking(World world, int x, int y, int z, Entity entity)
    {
        super.onEntityWalking(world, x, y, z, entity);

        LuacraftBlockHooks.onEntityWalking(this, world, x, y, z, entity);
    }

    @Override
    @LuaMetaHook
    public void onFallenUpon(World world, int x, int y, int z, Entity entity, float fallDistance)
    {
        super.onFallenUpon(world, x, y, z, entity, fallDistance);

        LuacraftBlockHooks.onFallenUpon(this, world, x, y, z, entity, fallDistance);
    }

    @Override
    @LuaMetaHook
    public void onNeighborChange(IBlockAccess world, int x, int y, int z, int tileX, int tileY, int tileZ)
    {
        super.onNeighborChange(world, x, y, z, tileX, tileY, tileZ);

        LuacraftBlockHooks.onNeighborChange(this, world, x, y, z, tileX, tileY, tileZ);
    }

    @Override
    @LuaMetaHook
    public void onPlantGrow(World world, int x, int y, int z, int sourceX, int sourceY, int sourceZ)
    {
        super.onPlantGrow(world, x, y, z, sourceX, sourceY, sourceZ);

        LuacraftBlockHooks.onPlantGrow(this, world, x, y, z, sourceX, sourceY, sourceZ);
    }

    @Override
    @LuaMetaHook
    public void onPostBlockPlaced(World world, int x, int y, int z, int metadata)
    {
        super.onPostBlockPlaced(world, x, y, z, metadata);

        LuacraftBlockHooks.onPostBlockPlaced(this, world, x, y, z, metadata);
    }

    @Override
    @LuaMetaHook
    public boolean canConnectRedstone(IBlockAccess world, int x, int y, int z, int side)
    {
        Boolean bool = LuacraftBlockHooks.canConnectRedstone(this, world, x, y, z, side);

        return bool == null ? super.canConnectRedstone(world, x, y, z, side) : bool;
    }

    @Override
    @LuaMetaHook
    public boolean canProvidePower()
    {
        Boolean bool = LuacraftBlockHooks.canProvidePower(this);

        return bool == null ? super.canProvidePower() : bool;
    }

    @Override
    @LuaMetaHook
    public boolean canEntityDestroy(IBlockAccess world, int x, int y, int z, Entity entity)
    {
        Boolean bool = LuacraftBlockHooks.canEntityDestroy(this, world, x, y, z, entity);

        return bool == null ? super.canEntityDestroy(world, x, y, z, entity) : bool;
    }

    @Override
    @LuaMetaHook
    public boolean canCreatureSpawn(EnumCreatureType type, IBlockAccess world, int x, int y, int z)
    {
        Boolean bool = LuacraftBlockHooks.canCreatureSpawn(this, type, world, x, y, z);

        return bool == null ? super.canCreatureSpawn(type, world, x, y, z) : bool;
    }

    @Override
    @LuaMetaHook
    public boolean canBlockStay(World world, int x, int y, int z)
    {
        Boolean bool = LuacraftBlockHooks.canBlockStay(this, world, x, y, z);

        return bool == null ? super.canBlockStay(world, x, y, z) : bool;
    }

    @Override
    @LuaMetaHook
    public boolean canPlaceTorchOnTop(World world, int x, int y, int z)
    {
        Boolean bool = LuacraftBlockHooks.canPlaceTorchOnTop(this, world, x, y, z);

        return bool == null ? super.canPlaceTorchOnTop(world, x, y, z) : bool;
    }

    @Override
    @LuaMetaHook
    public boolean canDropFromExplosion(Explosion explosion)
    {
        Boolean bool = LuacraftBlockHooks.canDropFromExplosion(this, explosion);

        return bool == null ? super.canDropFromExplosion(explosion) : bool;
    }

    @Override
    @LuaMetaHook
    public boolean canHarvestBlock(EntityPlayer player, int meta)
    {
        Boolean bool = LuacraftBlockHooks.canHarvestBlock(this, player, meta);

        return bool == null ? super.canHarvestBlock(player, meta) : bool;
    }

    @Override
    @LuaMetaHook
    public boolean canPlaceBlockAt(World world, int x, int y, int z)
    {
        Boolean bool = LuacraftBlockHooks.canPlaceBlockAt(this, world, x, y, z);

        return bool == null ? super.canPlaceBlockAt(world, x, y, z) : bool;
    }

    @Override
    @LuaMetaHook
    public boolean canBeReplacedByLeaves(IBlockAccess world, int x, int y, int z)
    {
        Boolean bool = LuacraftBlockHooks.canBeReplacedByLeaves(this, world, x, y, z);

        return bool == null ? super.canBeReplacedByLeaves(world, x, y, z) : bool;
    }

    @Override
    @LuaMetaHook
    public boolean canCollideCheck(int meta, boolean boat)
    {
        Boolean bool = LuacraftBlockHooks.canCollideCheck(this, meta, boat);

        return bool == null ? super.canCollideCheck(meta, boat) : bool;
    }

    @Override
    @LuaMetaHook
    public boolean canPlaceBlockOnSide(World world, int x, int y, int z, int side)
    {
        Boolean bool = LuacraftBlockHooks.canPlaceBlockOnSide(this, world, x, y, z, side);

        return bool == null ? super.canPlaceBlockOnSide(world, x, y, z, side) : bool;
    }

    @Override
    @LuaMetaHook
    public boolean canRenderInPass(int pass)
    {
        Boolean bool = LuacraftBlockHooks.canRenderInPass(this, pass);

        return bool == null ? super.canRenderInPass(pass) : bool;
    }

    @Override
    @LuaMetaHook
    public boolean canSustainLeaves(IBlockAccess world, int x, int y, int z)
    {
        Boolean bool = LuacraftBlockHooks.canSustainLeaves(this, world, x, y, z);

        return bool == null ? super.canSustainLeaves(world, x, y, z) : bool;
    }

    @Override
    @LuaMetaHook
    public boolean canSustainPlant(IBlockAccess world, int x, int y, int z, ForgeDirection direction,
                                   IPlantable plantable)
    {
        Boolean bool = LuacraftBlockHooks.canSustainPlant(this, world, x, y, z,
                direction, plantable);

        return bool == null ? super.canSustainPlant(world, x, y, z, direction, plantable) : bool;
    }

    @Override
    @LuaMetaHook
    public boolean canReplace(World world, int x, int y, int z, int side, ItemStack stack)
    {
        Boolean bool = LuacraftBlockHooks.canReplace(this, world, x, y, z,
                side, stack);

        return bool == null ? super.canReplace(world, x, y, z, side, stack) : bool;
    }

    @Override
    @LuaMetaHook
    public boolean canSilkHarvest(World world, EntityPlayer player, int x, int y, int z, int metadata)
    {
        Boolean bool = LuacraftBlockHooks.canSilkHarvest(this, world, player, x, y, z,
                metadata);

        return bool == null ? super.canSilkHarvest(world, player, x, y, z, metadata) : bool;
    }

    @Override
    public boolean hasTileEntity(int metadata)
    {
        return tileEntityClass != null;
    }

    @Override
    public TileEntity createTileEntity(World world, int metadata)
    {
        return new LuacraftTileEntity(tileEntityClass);
    }
}
