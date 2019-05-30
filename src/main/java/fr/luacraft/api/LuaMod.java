package fr.luacraft.api;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.luacraft.api.classes.LuacraftBlock;
import fr.luacraft.api.classes.LuacraftFluidBlock;
import fr.luacraft.api.classes.LuacraftItem;
import fr.luacraft.api.classes.LuacraftItemBucket;
import fr.luacraft.core.Luacraft;
import fr.luacraft.modloader.ILuaContainer;
import fr.luacraft.modloader.ILuaContainerObject;
import fr.luacraft.modloader.LuaGameRegistry;
import fr.luacraft.modloader.LuacraftMod;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;

public class LuaMod implements ILuaContainer
{
    private LuacraftMod mod;

    public LuaMod(LuacraftMod mod)
    {
        this.mod = mod;
    }

    public LuaBlock RegisterBlock(String id, int material)
    {
        LuaBlock block = new LuaBlock(new LuacraftBlock(id, material));
        LuaGameRegistry.register(id, block.getContainedObject());
        return block;
    }

    public LuaItem RegisterItem(String id)
    {
        LuaItem item = new LuaItem(new LuacraftItem(id));
        LuaGameRegistry.register(id, item.getContainedObject());
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
        LuaGameRegistry.register(id, block.getBlock());
        return block;
    }

    public LuaItem RegisterFluidBucket(String id, String fluidBlock, String fluid)
    {
        LuaItem item = new LuaItem(new LuacraftItemBucket(id, GameRegistry.findBlock(Luacraft.getInstance().getProxy().getCurrentMod().getModId(),
                fluidBlock)));
        LuaGameRegistry.register(id, item.getContainedObject());
        FluidContainerRegistry.registerFluidContainer(
                FluidRegistry.getFluidStack(fluid, FluidContainerRegistry.BUCKET_VOLUME),
                new ItemStack((LuacraftItemBucket) item.getItem()), FluidContainerRegistry.EMPTY_BUCKET);
        return item;
    }

    public void LogInfo(String message)
    {
        mod.getLogger().info(message);
    }

    @Override
    public String getType()
    {
        return "LuaMod";
    }

    @Override
    public ILuaContainerObject getContainedObject()
    {
        return mod;
    }
}
