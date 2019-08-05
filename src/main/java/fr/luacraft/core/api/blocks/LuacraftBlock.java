package fr.luacraft.core.api.blocks;

import fr.luacraft.core.Luacraft;
import fr.luacraft.core.api.LuaClass;
import fr.luacraft.core.api.entity.LuaEntityLivingBase;
import fr.luacraft.core.api.entity.LuaEntityPlayer;
import fr.luacraft.core.api.entity.LuacraftTileEntity;
import fr.luacraft.core.api.hooks.LuaHookManager;
import fr.luacraft.core.api.hooks.LuacraftBlockHooks;
import fr.luacraft.core.api.items.LuaItemStack;
import fr.luacraft.core.api.world.LuaWorld;
import fr.luacraft.util.LuaUtil;
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
    private LuaClass tileEntityClass;

    public LuacraftBlock(String name, int material, LuaClass tileEntityClass)
    {
        super(LuaUtil.getMaterialByID(material));

        this.tileEntityClass = tileEntityClass;

        this.setBlockName(name);
        this.setBlockTextureName(Luacraft.getInstance().getProxy().getCurrentMod().getModId() + ":" + name);
    }

    @Override
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
    public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player)
    {
        super.onBlockClicked(world, x, y, z, player);

        LuacraftBlockHooks.onBlockClicked(this, world, x, y, z, player);
    }

    @Override
    public boolean onBlockEventReceived(World world, int x, int y, int z, int side, int metadata)
    {
        super.onBlockEventReceived(world, x, y, z, side, metadata);

        return LuacraftBlockHooks.onBlockEventReceived(this, world, x, y, z, side, metadata);
    }

    @Override
    public void onBlockAdded(World world, int x, int y, int z)
    {
        super.onBlockAdded(world, x, y, z);

        LuacraftBlockHooks.onBlockAdded(this, world, x, y, z);
    }

    @Override
    public void onBlockDestroyedByExplosion(World world, int x, int y, int z, Explosion explosion)
    {
        super.onBlockDestroyedByExplosion(world, x, y, z, explosion);

        LuacraftBlockHooks.onBlockDestroyedByExplosion(this, world, x, y, z, explosion);
    }

    @Override
    public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int metadata)
    {
        super.onBlockDestroyedByPlayer(world, x, y, z, metadata);

        LuacraftBlockHooks.onBlockDestroyedByPlayer(this, world, x, y, z, metadata);
    }

    @Override
    public void onBlockExploded(World world, int x, int y, int z, Explosion explosion)
    {
        super.onBlockExploded(world, x, y, z, explosion);

        LuacraftBlockHooks.onBlockExploded(this, world, x, y, z, explosion);
    }

    @Override
    public void onBlockHarvested(World world, int x, int y, int z, int metadata, EntityPlayer player)
    {
        super.onBlockHarvested(world, x, y, z, metadata, player);

        LuacraftBlockHooks.onBlockHarvested(this, world, x, y, z, metadata, player);
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack)
    {
        super.onBlockPlacedBy(world, x, y, z, entity, stack);

        LuacraftBlockHooks.onBlockPlacedBy(this, world, x, y, z, entity, stack);
    }

    @Override
    public void onBlockPreDestroy(World world, int x, int y, int z, int metadata)
    {
        super.onBlockPreDestroy(world, x, y, z, metadata);

        LuacraftBlockHooks.onBlockPreDestroy(this, world, x, y, z, metadata);
    }

    @Override
    public void onNeighborBlockChange(World world, int x, int y, int z, Block otherBlock)
    {
        super.onNeighborBlockChange(world, x, y, z, otherBlock);

        LuacraftBlockHooks.onNeighborBlockChange(this, world, x, y, z, otherBlock);
    }

    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
    {
        super.onEntityCollidedWithBlock(world, x, y, z, entity);

        LuacraftBlockHooks.onEntityCollidedWithBlock(this, world, x, y, z, entity);
    }

    @Override
    public void onEntityWalking(World world, int x, int y, int z, Entity entity)
    {
        super.onEntityWalking(world, x, y, z, entity);

        LuacraftBlockHooks.onEntityWalking(this, world, x, y, z, entity);
    }

    @Override
    public void onFallenUpon(World world, int x, int y, int z, Entity entity, float fallDistance)
    {
        super.onFallenUpon(world, x, y, z, entity, fallDistance);

        LuacraftBlockHooks.onFallenUpon(this, world, x, y, z, entity, fallDistance);
    }

    @Override
    public void onNeighborChange(IBlockAccess world, int x, int y, int z, int tileX, int tileY, int tileZ)
    {
        super.onNeighborChange(world, x, y, z, tileX, tileY, tileZ);

        LuacraftBlockHooks.onNeighborChange(this, world, x, y, z, tileX, tileY, tileZ);
    }

    @Override
    public void onPlantGrow(World world, int x, int y, int z, int sourceX, int sourceY, int sourceZ)
    {
        super.onPlantGrow(world, x, y, z, sourceX, sourceY, sourceZ);

        LuacraftBlockHooks.onPlantGrow(this, world, x, y, z, sourceX, sourceY, sourceZ);
    }

    @Override
    public void onPostBlockPlaced(World world, int x, int y, int z, int metadata)
    {
        super.onPostBlockPlaced(world, x, y, z, metadata);

        LuacraftBlockHooks.onPostBlockPlaced(this, world, x, y, z, metadata);
    }

    @Override
    public boolean canConnectRedstone(IBlockAccess world, int x, int y, int z, int side)
    {
        Boolean bool = LuacraftBlockHooks.canConnectRedstone(this, world, x, y, z, side);

        return bool == null ? super.canConnectRedstone(world, x, y, z, side) : bool;
    }

    @Override
    public boolean canProvidePower()
    {
        Boolean bool = LuacraftBlockHooks.canProvidePower(this);

        return bool == null ? super.canProvidePower() : bool;
    }

    @Override
    public boolean canEntityDestroy(IBlockAccess world, int x, int y, int z, Entity entity)
    {
        Boolean bool = LuacraftBlockHooks.canEntityDestroy(this, world, x, y, z, entity);

        return bool == null ? super.canEntityDestroy(world, x, y, z, entity) : bool;
    }

    @Override
    public boolean canCreatureSpawn(EnumCreatureType type, IBlockAccess world, int x, int y, int z)
    {
        Boolean bool = LuacraftBlockHooks.canCreatureSpawn(this, type, world, x, y, z);

        return bool == null ? super.canCreatureSpawn(type, world, x, y, z) : bool;
    }

    @Override
    public boolean canBlockStay(World world, int x, int y, int z)
    {
        Boolean bool = LuacraftBlockHooks.canBlockStay(this, world, x, y, z);

        return bool == null ? super.canBlockStay(world, x, y, z) : bool;
    }

    @Override
    public boolean canPlaceTorchOnTop(World world, int x, int y, int z)
    {
        Boolean bool = LuacraftBlockHooks.canPlaceTorchOnTop(this, world, x, y, z);

        return bool == null ? super.canPlaceTorchOnTop(world, x, y, z) : bool;
    }

    @Override
    public boolean canDropFromExplosion(Explosion explosion)
    {
        Boolean bool = LuacraftBlockHooks.canDropFromExplosion(this, explosion);

        return bool == null ? super.canDropFromExplosion(explosion) : bool;
    }

    @Override
    public boolean canHarvestBlock(EntityPlayer player, int meta)
    {
        Boolean bool = LuacraftBlockHooks.canHarvestBlock(this, player, meta);

        return bool == null ? super.canHarvestBlock(player, meta) : bool;
    }

    @Override
    public boolean canPlaceBlockAt(World world, int x, int y, int z)
    {
        Boolean bool = LuacraftBlockHooks.canPlaceBlockAt(this, world, x, y, z);

        return bool == null ? super.canPlaceBlockAt(world, x, y, z) : bool;
    }

    @Override
    public boolean canBeReplacedByLeaves(IBlockAccess world, int x, int y, int z)
    {
        Boolean bool = LuacraftBlockHooks.canBeReplacedByLeaves(this, world, x, y, z);

        return bool == null ? super.canBeReplacedByLeaves(world, x, y, z) : bool;
    }

    @Override
    public boolean canCollideCheck(int meta, boolean boat)
    {
        Boolean bool = LuacraftBlockHooks.canCollideCheck(this, meta, boat);

        return bool == null ? super.canCollideCheck(meta, boat) : bool;
    }

    @Override
    public boolean canPlaceBlockOnSide(World world, int x, int y, int z, int side)
    {
        Boolean bool = LuacraftBlockHooks.canPlaceBlockOnSide(this, world, x, y, z, side);

        return bool == null ? super.canPlaceBlockOnSide(world, x, y, z, side) : bool;
    }

    @Override
    public boolean canRenderInPass(int pass)
    {
        Boolean bool = LuacraftBlockHooks.canRenderInPass(this, pass);

        return bool == null ? super.canRenderInPass(pass) : bool;
    }

    @Override
    public boolean canSustainLeaves(IBlockAccess world, int x, int y, int z)
    {
        Boolean bool = LuacraftBlockHooks.canSustainLeaves(this, world, x, y, z);

        return bool == null ? super.canSustainLeaves(world, x, y, z) : bool;
    }

    @Override
    public boolean canSustainPlant(IBlockAccess world, int x, int y, int z, ForgeDirection direction,
                                   IPlantable plantable)
    {
        Boolean bool = LuacraftBlockHooks.canSustainPlant(this, world, x, y, z,
                direction, plantable);

        return bool == null ? super.canSustainPlant(world, x, y, z, direction, plantable) : bool;
    }

    @Override
    public boolean canReplace(World world, int x, int y, int z, int side, ItemStack stack)
    {
        Boolean bool = LuacraftBlockHooks.canReplace(this, world, x, y, z,
                side, stack);

        return bool == null ? super.canReplace(world, x, y, z, side, stack) : bool;
    }

    @Override
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
