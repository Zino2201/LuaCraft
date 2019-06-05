package fr.luacraft.core.api.items;

import cpw.mods.fml.relauncher.Side;
import fr.luacraft.core.Luacraft;
import fr.luacraft.core.api.ILuaObject;
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
     * @param label
     */
    public void SetCreativeTab(String label)
    {
        if(Luacraft.getInstance().getProxy().getSide() == Side.CLIENT)
        {
            for (CreativeTabs tab : CreativeTabs.creativeTabArray)
            {
                if (tab.getTabLabel().equals(label))
                {
                    item.setCreativeTab(tab);
                }
            }
        }
    }

    public Item getItem()
    {
        return item;
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
