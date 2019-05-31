package fr.luacraft.api.classes;

import fr.luacraft.api.LuaBlock;
import fr.luacraft.api.LuaTileEntity;
import fr.luacraft.core.LuaHookManager;
import fr.luacraft.core.Luacraft;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * A Luacraft block compatible with Luacraft hooks
 */
public class LuacraftBlock extends Block
{
    private TileEntity tileEntity;

    public LuacraftBlock(String name, int material, LuaTileEntity tileEntity)
    {
        super(LuaBlock.getMaterialFromID(material));

        if(tileEntity != null)
            this.tileEntity = (TileEntity) tileEntity.getObject();

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
        if (!world.isRemote)
        {
            LuaHookManager.call(this, "OnBlockActivated", world, x, y, z, player);

            return true;
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
