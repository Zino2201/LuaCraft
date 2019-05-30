package fr.luacraft.api.classes;

import fr.luacraft.api.IBlockContainerObject;
import fr.luacraft.api.LuaBlock;
import fr.luacraft.core.Luacraft;
import fr.luacraft.modloader.ContainerObjectType;
import net.minecraft.block.Block;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.FluidRegistry;

public class LuacraftFluidBlock extends BlockFluidClassic implements IBlockContainerObject
{
    public LuacraftFluidBlock(String name, String fluid, int material)
    {
        super(FluidRegistry.getFluid(fluid), LuaBlock.getMaterialFromID(material));

        this.setBlockName(name);
        this.setBlockTextureName(Luacraft.getInstance().getProxy().getCurrentMod().getModId() + ":" + name);

        FluidRegistry.getFluid(fluid).setBlock(this);
    }

    @Override
    public boolean canDisplace(IBlockAccess world, int x, int y, int z)
    {
        if(world.getBlock(x, y, z).getMaterial().isLiquid())
        {
            return false;
        }

        return super.canDisplace(world, x, y, z);
    }

    @Override
    public boolean displaceIfPossible(World world, int x, int y, int z)
    {
        if(world.getBlock(x, y, z).getMaterial().isLiquid())
        {
            return false;
        }

        return super.displaceIfPossible(world, x, y, z);
    }

    @Override
    public ContainerObjectType getType()
    {
        return ContainerObjectType.BLOCK;
    }

    @Override
    public Block getBlock()
    {
        return this;
    }
}
