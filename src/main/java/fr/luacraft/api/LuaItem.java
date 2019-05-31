package fr.luacraft.api;

import fr.luacraft.modloader.ILuaObject;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class LuaItem implements ILuaObject
{
    private Item item;

    public LuaItem(Item item)
    {
        this.item = item;
    }

    public void SetCreativeTab(String label)
    {
        for(CreativeTabs tab : CreativeTabs.creativeTabArray)
        {
            if (tab.getTabLabel().equals(label))
            {
                item.setCreativeTab(tab);
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
