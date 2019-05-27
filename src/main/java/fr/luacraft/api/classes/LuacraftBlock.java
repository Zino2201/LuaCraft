package fr.luacraft.api.classes;

import fr.luacraft.core.LuaHookManager;
import fr.luacraft.core.Luacraft;
import fr.luacraft.modloader.ContainerObjectType;
import fr.luacraft.modloader.ILuaContainerObject;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * A Luacraft block compatible with Luacraft functions
 */
public class LuacraftBlock extends Block implements ILuaContainerObject
{
    public LuacraftBlock(String name)
    {
        super(Material.wood);

        this.setBlockName(name);
        this.setBlockTextureName(Luacraft.getInstance().getProxy().getCurrentMod().getModId() + ":" + name);
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase placer, ItemStack stack)
    {
        super.onBlockPlacedBy(world, x, y, z, placer, stack);

        LuaHookManager.call(this, "OnBlockPlacedBy", world, x, y, z, placer, stack);
    }

    @Override
    public ContainerObjectType getType()
    {
        return ContainerObjectType.BLOCK;
    }
}
