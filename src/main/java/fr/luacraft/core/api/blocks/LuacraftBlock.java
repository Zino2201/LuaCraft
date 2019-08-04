package fr.luacraft.core.api.blocks;

import fr.luacraft.core.Luacraft;
import fr.luacraft.core.api.LuaClass;
import fr.luacraft.core.api.entity.LuaEntityLivingBase;
import fr.luacraft.core.api.entity.LuaEntityPlayer;
import fr.luacraft.core.api.entity.LuacraftTileEntity;
import fr.luacraft.core.api.hooks.LuaHookManager;
import fr.luacraft.core.api.items.LuaItemStack;
import fr.luacraft.core.api.world.LuaWorld;
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
    private LuaClass tileEntityClass;

    public LuacraftBlock(String name, int material, LuaClass tileEntityClass)
    {
        super(LuaUtil.getMaterialByID(material));

        this.tileEntityClass = tileEntityClass;

        this.setBlockName(name);
        this.setBlockTextureName(Luacraft.getInstance().getProxy().getCurrentMod().getModId() + ":" + name);
    }

    // TODO: (Re-)Implement functions & hooks

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
