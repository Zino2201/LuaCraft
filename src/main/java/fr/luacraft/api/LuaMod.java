package fr.luacraft.api;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.luacraft.api.classes.*;
import fr.luacraft.core.Luacraft;
import fr.luacraft.modloader.ILuaObject;
import fr.luacraft.modloader.LuaGameRegistry;
import fr.luacraft.modloader.LuacraftMod;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;

/**
 * Represents a LuacraftMod in Lua
 * @author Zino
 */
public class LuaMod implements ILuaObject
{
    /**
     * Contained mod
     */
    private LuacraftMod mod;

    public LuaMod(LuacraftMod mod)
    {
        this.mod = mod;
    }

    /**
     * Register a block
     * @param id
     * @param material
     * @return
     */
    public LuaBlock RegisterBlock(String id, int material)
    {
        LuaBlock block = new LuaBlock(new LuacraftBlock(id, material, null));
        LuaGameRegistry.registerBlock(id, block.getBlock());
        return block;
    }

    /**
     * Register a block with TileEntity
     * @param id
     * @param material
     * @param tileEntity
     * @return
     */
    public LuaBlock RegisterBlock(String id, int material, LuaTileEntity tileEntity)
    {
        LuaBlock block = new LuaBlock(new LuacraftBlock(id, material, tileEntity));
        LuaGameRegistry.registerBlock(id, block.getBlock());
        return block;
    }

    /**
     * Register a item
     * @param id
     * @return
     */
    public LuaItem RegisterItem(String id)
    {
        LuaItem item = new LuaItem(new LuacraftItem(id));
        LuaGameRegistry.registerItem(id, item.getItem());
        return item;
    }

    /**
     * Register a ore
     * @param id
     * @param dimensionID
     * @param minY
     * @param maxY
     * @param veinSize
     * @param chances
     */
    public void RegisterOre(String id, int dimensionID, int minY, int maxY, int veinSize, int chances)
    {
        mod.getRegistryData().addOre(id, dimensionID, minY, maxY, veinSize, chances);
    }

    /**
     * Register a fluid
     * @param id
     * @param density
     * @param viscosity
     * @param temperature
     * @param luminosity
     * @return
     */
    public LuaFluid RegisterFluid(String id, int density, int viscosity, int temperature, int luminosity)
    {
        Fluid fluid = new Fluid(id).setDensity(density).setViscosity(viscosity).setTemperature(temperature)
                .setLuminosity(luminosity).setUnlocalizedName(id);
        FluidRegistry.registerFluid(fluid);
        fluid = FluidRegistry.getFluid(id);
        return new LuaFluid(fluid);
    }

    /**
     * Register a fluid block
     * @param id
     * @param fluid
     * @param material
     * @return
     */
    public LuaBlock RegisterFluidBlock(String id, String fluid, int material)
    {
        LuaBlock block = new LuaBlock(new LuacraftFluidBlock(id, fluid, material));
        LuaGameRegistry.registerBlock(id, block.getBlock());
        return block;
    }

    /**
     * Register a fluid bucket
     * @param id
     * @param fluidBlock
     * @param fluid
     * @return
     */
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

    /**
     * Add a creative tab
     * @param label
     * @param item
     */
    public void AddCreativeTab(String label, final LuaItem item)
    {
        CreativeTabs creativeTab = new CreativeTabs(label)
        {
            @Override
            public Item getTabIconItem()
            {
                return item.getItem();
            }

            @SideOnly(Side.CLIENT)
            public int func_151243_f()
            {
                return 0;
            }
        };

        mod.getRegistryData().addCreativeTab(creativeTab);
    }

    /**
     * Create a LuacraftTileEntity
     * @return
     */
    public LuaTileEntity CreateTileEntity()
    {
        LuaTileEntity tileEntity = new LuaTileEntity(new LuacraftTileEntity());
        return tileEntity;
    }

    /**
     * Log to the console
     * @param message
     */
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
