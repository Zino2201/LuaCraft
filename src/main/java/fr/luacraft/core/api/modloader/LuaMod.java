package fr.luacraft.core.api.modloader;

import com.naef.jnlua.util.LuaFunction;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.luacraft.core.Luacraft;
import fr.luacraft.core.api.ILuaContainer;
import fr.luacraft.core.api.blocks.LuaBlock;
import fr.luacraft.core.api.blocks.LuacraftBlock;
import fr.luacraft.core.api.command.LuaCommand;
import fr.luacraft.core.api.command.LuacraftCommand;
import fr.luacraft.core.api.creativetab.LuaCreativeTab;
import fr.luacraft.core.api.fluids.LuaFluid;
import fr.luacraft.core.api.fluids.LuacraftFluidBlock;
import fr.luacraft.core.api.items.*;
import fr.luacraft.core.api.reflection.LuaJavaObject;
import fr.luacraft.core.api.registry.LuaGameRegistry;
import fr.luacraft.modloader.LuacraftMod;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.client.EnumHelperClient;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;

import static cpw.mods.fml.common.registry.GameRegistry.findBlock;
import static cpw.mods.fml.relauncher.Side.CLIENT;
import static fr.luacraft.core.Luacraft.getInstance;
import static fr.luacraft.core.api.registry.LuaGameRegistry.registerBlock;
import static fr.luacraft.core.api.registry.LuaGameRegistry.registerItem;
import static net.minecraftforge.client.ClientCommandHandler.instance;
import static net.minecraftforge.common.util.EnumHelper.addArmorMaterial;
import static net.minecraftforge.common.util.EnumHelper.addToolMaterial;
import static net.minecraftforge.fluids.FluidContainerRegistry.*;
import static net.minecraftforge.fluids.FluidRegistry.*;

/**
 * Represents a LuacraftMod in Lua
 * @author Zino
 */
@SuppressWarnings("unused")
public class LuaMod implements ILuaContainer
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
    @LuaFunction
    public LuaBlock RegisterBlock(String id, int material)
    {
        Block block = mod.getRegistryData().getBlockByID(id);

        if(block == null)
        {
            LuaBlock luaBlock = new LuaBlock(new LuacraftBlock(id, material, null));
            registerBlock(id, luaBlock.getBlock());
            return luaBlock;
        }
        else
        {
            return new LuaBlock(block);
        }
    }

    /**
     * Register a item
     * @param id
     * @return
     */
    @LuaFunction
    public LuaItem RegisterItem(String id)
    {
        LuaItem item = new LuaItem(new LuacraftItem(id));
        registerItem(id, item.getItem());
        return item;
    }

    /**
     * Register a sword
     * @param id
     * @param material
     * @return
     */
    @LuaFunction
    public LuaItem RegisterItemSword(String id, String material)
    {
        LuaItem item = new LuaItem(new LuacraftItemSword(id, material));
        registerItem(id, item.getItem());
        return item;
    }

    /**
     * Register a pickaxe
     * @param id
     * @param material
     * @return
     */
    @LuaFunction
    public LuaItem RegisterItemPickaxe(String id, String material)
    {
        LuaItem item = new LuaItem(new LuacraftItemPickaxe(id, material));
        registerItem(id, item.getItem());
        return item;
    }

    /**
     * Register a axe
     * @param id
     * @param material
     * @return
     */
    @LuaFunction
    public LuaItem RegisterItemAxe(String id, String material)
    {
        LuaItem item = new LuaItem(new LuacraftItemAxe(id, material));
        registerItem(id, item.getItem());
        return item;
    }

    /**
     * Register a shovel
     * @param id
     * @param material
     * @return
     */
    @LuaFunction
    public LuaItem RegisterItemShovel(String id, String material)
    {
        LuaItem item = new LuaItem(new LuacraftItemShovel(id, material));
        registerItem(id, item.getItem());
        return item;
    }

    /**
     * Register a hoe
     * @param id
     * @param material
     * @return
     */
    @LuaFunction
    public LuaItem RegisterItemHoe(String id, String material)
    {
        LuaItem item = new LuaItem(new LuacraftItemHoe(id, material));
        registerItem(id, item.getItem());
        return item;
    }

    /**
     * Register a item armor
     * @param id
     * @param material
     * @return
     */
    @LuaFunction
    public LuaItem RegisterItemArmor(String id, String material, int part)
    {
        LuaItem item = new LuaItem(new LuacraftItemArmor(id, material, part));
        registerItem(id, item.getItem());
        return item;
    }

    /**
     * Register a item food
     * @param id
     * @param healAmount
     * @param saturation
     * @param isWolfFavoriteFood
     * @return
     */
    @LuaFunction
    public LuaItem RegisterItemFood(String id, int healAmount, float saturation, boolean isWolfFavoriteFood)
    {
        LuaItem item = new LuaItem(new LuacraftItemFood(id, healAmount, saturation, isWolfFavoriteFood));
        registerItem(id, item.getItem());
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
    @LuaFunction
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
    @LuaFunction
    public LuaFluid RegisterFluid(String id, int density, int viscosity, int temperature, int luminosity)
    {
        Fluid fluid = new Fluid(id).setDensity(density).setViscosity(viscosity).setTemperature(temperature)
                .setLuminosity(luminosity).setUnlocalizedName(id);
        registerFluid(fluid);
        fluid = getFluid(id);
        return new LuaFluid(fluid);
    }

    /**
     * Register a fluid block
     * @param id
     * @param fluid
     * @param material
     * @return
     */
    @LuaFunction
    public LuaBlock RegisterFluidBlock(String id, String fluid, int material)
    {
        LuaBlock block = new LuaBlock(new LuacraftFluidBlock(id, fluid, material));
        registerBlock(id, block.getBlock());
        return block;
    }

    /**
     * Register a fluid bucket
     * @param id
     * @param fluidBlock
     * @param fluid
     * @return
     */
    @LuaFunction
    public LuaItem RegisterFluidBucket(String id, String fluidBlock, String fluid)
    {
        LuaItem item = new LuaItem(new LuacraftItemBucket(id, findBlock(getInstance().getModLoader().getCurrentMod().getModId(),
                fluidBlock)));
        registerItem(id, item.getItem());
        registerFluidContainer(
                getFluidStack(fluid, BUCKET_VOLUME),
                new ItemStack(item.getItem()), EMPTY_BUCKET);
        return item;
    }

    /**
     * Add a creative tab
     * @param label
     * @param item
     */
    @LuaFunction
    public LuaCreativeTab AddCreativeTab(String label, final LuaItem item)
    {
        if(getInstance().getProxy().getSide() == CLIENT)
        {
            CreativeTabs creativeTab = new CreativeTabs(label) {
                @Override
                public Item getTabIconItem() {
                    return item.getItem();
                }

                @SideOnly(CLIENT)
                public int func_151243_f()
                {
                    return 0;
                }
            };

            mod.getRegistryData().addCreativeTab(creativeTab);

            return new LuaCreativeTab(creativeTab);
        }

        return null;
    }

    /**
     * Register a client-only command
     * @param name
     * @param usage
     * @return
     */
    @LuaFunction
    public LuaCommand RegisterClientCommand(String name, String usage)
    {
        LuacraftCommand command = new LuacraftCommand(name, usage);
        instance.registerCommand(command);
        return new LuaCommand(command);
    }

    /**
     * Register a server command
     * @param name
     * @param usage
     * @return
     */
    @LuaFunction
    public LuaCommand RegisterServerCommand(String name, String usage)
    {
        LuacraftCommand command = new LuacraftCommand(name, usage);
        mod.getRegistryData().addServerCommand(command);
        return new LuaCommand(command);
    }

    /**
     * Register a tool material
     * @param name
     * @param harvestLevel
     * @param maxUses
     * @param efficiency
     * @param damage
     * @param enchantability
     */
    @LuaFunction
    public void RegisterToolMaterial(String name, int harvestLevel, int maxUses, float efficiency, float damage, int enchantability)
    {
        addToolMaterial(name, harvestLevel, maxUses, efficiency, damage, enchantability);
    }

    /**
     * Register a armor material
     * @param name
     * @param durability
     * @param reductionAmounts
     * @param enchantability
     */
    @LuaFunction
    public void RegisterArmorMaterial(String name, int durability, int[] reductionAmounts, int enchantability)
    {
        addArmorMaterial(name, durability, reductionAmounts, enchantability);
    }

    /**
     * Log debug
     * @param message
     */
    @LuaFunction
    public void LogDebug(String message) { mod.getLogger().debug(message); }

    /**
     * Log info
     * @param message
     */
    @LuaFunction
    public void LogInfo(String message) { mod.getLogger().info(message); }

    /**
     * Log warn
     * @param message
     */
    @LuaFunction
    public void LogWarn(String message) { mod.getLogger().warn(message); }

    /**
     * Log error
     * @param message
     */
    @LuaFunction
    public void LogError(String message) { mod.getLogger().error(message); }

    /**
     * Log fatal
     * @param message
     */
    @LuaFunction
    public void LogFatal(String message) { mod.getLogger().fatal(message); }

    /**
     * Get mod id
     * @return
     */
    @LuaFunction
    public String GetModId()
    {
        return mod.getModId();
    }

    /**
     * Get mod name
     * @return
     */
    @LuaFunction
    public String GetName()
    {
        return mod.getName();
    }

    /**
     * Get mod version
     * @return
     */
    @LuaFunction
    public String GetVersion()
    {
        return mod.getVersion();
    }

    /**
     * Get luacraft version
     * @return
     */
    @LuaFunction
    public String GetLuacraftVersion()
    {
        return mod.getLuacraftVersion();
    }

    @Override
    @LuaFunction
    public String GetTypeName()
    {
        return "LuaMod";
    }

    @Override
    @LuaFunction
    public LuaJavaObject GetContainedObject()
    {
        return new LuaJavaObject(mod);
    }
}
