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
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

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
