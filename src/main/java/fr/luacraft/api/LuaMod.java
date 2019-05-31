package fr.luacraft.api;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.luacraft.api.classes.*;
import fr.luacraft.core.Luacraft;
import fr.luacraft.modloader.ILuaObject;
import fr.luacraft.modloader.LuaGameRegistry;
import fr.luacraft.modloader.LuacraftMod;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;

public class LuaMod implements ILuaObject
{
    private LuacraftMod mod;

    public LuaMod(LuacraftMod mod)
    {
        this.mod = mod;
    }

    public LuaBlock RegisterBlock(String id, int material)
    {
        LuaBlock block = new LuaBlock(new LuacraftBlock(id, material, null));
        LuaGameRegistry.registerBlock(id, block.getBlock());
        return block;
    }

    public LuaBlock RegisterBlock(String id, int material, LuaTileEntity tileEntity)
    {
        LuaBlock block = new LuaBlock(new LuacraftBlock(id, material, tileEntity));
        LuaGameRegistry.registerBlock(id, block.getBlock());
        return block;
    }

    public LuaItem RegisterItem(String id)
    {
        LuaItem item = new LuaItem(new LuacraftItem(id));
        LuaGameRegistry.registerItem(id, item.getItem());
        return item;
    }

    public void RegisterOre(String id, int dimensionID, int minY, int maxY, int veinSize, int chances)
    {
        mod.getRegistryData().addOre(id, dimensionID, minY, maxY, veinSize, chances);
    }

    public LuaFluid RegisterFluid(String id, int density, int viscosity, int temperature, int luminosity)
    {
        Fluid fluid = new Fluid(id).setDensity(density).setViscosity(viscosity).setTemperature(temperature)
                .setLuminosity(luminosity).setUnlocalizedName(id);
        FluidRegistry.registerFluid(fluid);
        fluid = FluidRegistry.getFluid(id);
        return new LuaFluid(fluid);
    }

    public LuaBlock RegisterFluidBlock(String id, String fluid, int material)
    {
        LuaBlock block = new LuaBlock(new LuacraftFluidBlock(id, fluid, material));
        LuaGameRegistry.registerBlock(id, block.getBlock());
        return block;
    }

    public LuaItem RegisterFluidBucket(String id, String fluidBlock, String fluid)
    {
        LuaItem item = new LuaItem(new LuacraftItemBucket(id, GameRegistry.findBlock(Luacraft.getInstance().getProxy().getCurrentMod().getModId(),
                fluidBlock)));
        LuaGameRegistry.registerItem(id, item.getItem());
        FluidContainerRegistry.registerFluidContainer(
                FluidRegistry.getFluidStack(fluid, FluidContainerRegistry.BUCKET_VOLUME),
                new ItemStack(item.getItem()), FluidContainerRegistry.EMPTY_BUCKET);
        return item;
    }

    public LuaTileEntity CreateTileEntity()
    {
        LuaTileEntity tileEntity = new LuaTileEntity(new LuacraftTileEntity());
        return tileEntity;
    }

    public void LogInfo(String message)
    {
        mod.getLogger().info(message);
    }

    @Override
    public String GetType()
    {
        return "LuaMod";
    }

    @Override
    public Object getObject()
    {
        return mod;
    }
}
