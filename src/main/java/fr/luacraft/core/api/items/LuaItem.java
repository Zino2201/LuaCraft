package fr.luacraft.core.api.items;

import com.naef.jnlua.util.LuaFunction;
import fr.luacraft.core.api.ILuaObject;
import fr.luacraft.core.api.blocks.LuaBlock;
import fr.luacraft.core.api.creativetab.LuaCreativeTab;
import fr.luacraft.core.api.util.LuaIIcon;
import fr.luacraft.util.LuaUtil;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Represetns a item in lua
 * @author Zino
 */
public class LuaItem implements ILuaObject
{
    private Item item;

    public LuaItem(Item item)
    {
        this.item = item;
    }

    /**
     * Set item creative tab
     * @param tab
     */
    @LuaFunction
    public void SetCreativeTab(LuaCreativeTab tab)
    {
        item.setCreativeTab(tab.getCreativeTabs());
    }

    /**
     * Disable repair on item
     */
    @LuaFunction
    public void SetNoRepair()
    {
        item.setNoRepair();
    }

    /**
     * Set item max stack size
     * @param stackSize
     */
    @LuaFunction
    public void SetMaxStackSize(int stackSize)
    {
        item.setMaxStackSize(stackSize);
    }

    /**
     * Set harvest level with specified tool
     * @param tool
     * @param level
     */
    @LuaFunction
    public void SetHarvestLevel(String tool, int level)
    {
        item.setHarvestLevel(tool, level);
    }

    /**
     * Set item max damage
     * @param maxDamage
     */
    @LuaFunction
    public void SetMaxDamage(int maxDamage)
    {
        item.setMaxDamage(maxDamage);
    }

    /**
     * Set item potion effect
     * @param effect
     */
    @LuaFunction
    public void SetPotionEffect(String effect)
    {
        item.setPotionEffect(effect);
    }

    /**
     * Set if item is a map
     * NOTE: Only works with LuacraftItems
     * @param map
     */
    @LuaFunction
    public void SetIsMap(boolean map)
    {
        if(item instanceof LuacraftItem)
            ((LuacraftItem) item).setIsMap(map);
    }

    /**
     * Set if item should be damageable
     * NOTE: Only works with LuacraftItems
     * @param dmgable
     */
    @LuaFunction
    public void SetDamageable(boolean dmgable)
    {
        if(item instanceof LuacraftItem)
            ((LuacraftItem) item).setDamageable(dmgable);
    }

    /**
     * Set if item should be repairable
     * NOTE: Only works with LuacraftItems
     * @param repable
     */
    @LuaFunction
    public void SetRepairable(boolean repable)
    {
        if(item instanceof LuacraftItem)
            ((LuacraftItem) item).setRepairable(repable);
    }

    /**
     * Get sprite number
     * @return
     */
    @LuaFunction
    public int GetSpriteNumber()
    {
        return item.getSpriteNumber();
    }

    /**
     * Get creative tab
     * @return
     */
    @LuaFunction
    public LuaCreativeTab GetCreativeTab()
    {
        return new LuaCreativeTab(item.getCreativeTab());
    }

    /**
     * Get item icon for specified render pass and stack
     * @param stack
     * @param pass
     * @return
     */
    @LuaFunction
    public LuaIIcon GetIcon(LuaItemStack stack, int pass)
    {
        return new LuaIIcon(item.getIcon(stack.getItemStack(), pass));
    }

    /**
     * Get unlocalized name
     * @return
     */
    @LuaFunction
    public String GetUnlocalizedName()
    {
        return item.getUnlocalizedName();
    }

    /**
     * Get item max damage
     * @return
     */
    @LuaFunction
    public int GetMaxDamage()
    {
        return item.getMaxDamage();
    }

    /**
     * Get item icons
     * @return
     */
    @LuaFunction
    public LuaIIcon GetIcon()
    {
        return new LuaIIcon(item.getIconFromDamage(0));
    }

    /**
     * Get harvest level
     * @param stack
     * @param toolClass
     * @return
     */
    @LuaFunction
    public int GetHarvestLevel(LuaItemStack stack, String toolClass)
    {
        return item.getHarvestLevel(stack.getItemStack(), toolClass);
    }

    /**
     * Get smelting experience
     * @param stack
     * @return
     */
    @LuaFunction
    public float GetSmeltingExperience(LuaItemStack stack)
    {
        return item.getSmeltingExperience(stack.getItemStack());
    }

    /**
     * Get container item
     * @return
     */
    @LuaFunction
    public LuaItem GetContainerItem()
    {
        return new LuaItem(item.getContainerItem());
    }

    /**
     * Get dig speed
     * @param stack
     * @param block
     * @param metadata
     * @return
     */
    @LuaFunction
    public float GetDigSpeed(LuaItemStack stack, LuaBlock block, int metadata)
    {
        return item.getDigSpeed(stack.getItemStack(), block.getBlock(), metadata);
    }

    /**
     * Get item rarity
     * @param stack
     * @return
     */
    @LuaFunction
    public int GetRarity(LuaItemStack stack)
    {
        return LuaUtil.getRarityId(item.getRarity(stack.getItemStack()));
    }

    public Item getItem()
    {
        return item;
    }

    @Override
    public boolean isContainer()
    {
        return true;
    }

    @Override
    public Object getObject()
    {
        return item;
    }

    @Override
    public String GetType()
    {
        return "Item";
    }
}
