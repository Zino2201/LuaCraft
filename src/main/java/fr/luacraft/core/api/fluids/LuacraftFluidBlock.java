package fr.luacraft.core.api.fluids;

import fr.luacraft.core.Luacraft;
import fr.luacraft.util.EnumUtil;
import fr.luacraft.util.LuaUtil;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.FluidRegistry;

/**
 * A fluid block compatible with Luacraft hooks
 * @author Zino
 */
public class LuacraftFluidBlock extends BlockFluidClassic
{
    public LuacraftFluidBlock(String name, String fluid, int material)
    {
        super(FluidRegistry.getFluid(fluid), EnumUtil.getMaterialFromInt(material));

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
}
