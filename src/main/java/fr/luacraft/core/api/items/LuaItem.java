package fr.luacraft.core.api.items;

import com.naef.jnlua.util.LuaFunction;
import cpw.mods.fml.relauncher.Side;
import fr.luacraft.core.Luacraft;
import fr.luacraft.core.api.ILuaObject;
import fr.luacraft.core.api.LuaCreativeTab;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

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
