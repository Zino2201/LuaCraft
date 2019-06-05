package fr.luacraft.core.api.blocks;

import fr.luacraft.core.Luacraft;
import fr.luacraft.core.api.entity.LuaEntityPlayer;
import fr.luacraft.core.api.entity.LuaTileEntity;
import fr.luacraft.core.api.hooks.LuaHookManager;
import fr.luacraft.util.LuaUtil;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * A Luacraft block compatible with Luacraft hooks
 * @author Zino
 */
public class LuacraftBlock extends Block
{
    private TileEntity tileEntity;
    private boolean canBeActivated;

    public LuacraftBlock(String name, int material, LuaTileEntity tileEntity)
    {
        super(LuaUtil.getMaterialByID(material));

        if(tileEntity != null)
            this.tileEntity = tileEntity.getTileEntity();

        this.setBlockName(name);
        this.setBlockTextureName(Luacraft.getInstance().getProxy().getCurrentMod().getModId() + ":" + name);
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase placer, ItemStack stack)
    {
        if(!world.isRemote)
        {
            LuaHookManager.call(this, "OnBlockPlacedBy", world, x, y, z, placer, stack);
        }
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
        if(LuaHookManager.hasHooks(this, "OnBlockActivated"))
        {
            if (!world.isRemote)
            {
                LuaHookManager.call(this, "OnBlockActivated", world, x, y, z, new LuaEntityPlayer(player));
                return true;
            }
        }
        
        return false;
    }

    @Override
    public boolean hasTileEntity(int metadata)
    {
        return tileEntity != null;
    }

    @Override
    public TileEntity createTileEntity(World world, int metadata)
    {
        return tileEntity;
    }
}