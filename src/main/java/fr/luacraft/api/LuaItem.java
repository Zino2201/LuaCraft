package fr.luacraft.api;

import fr.luacraft.api.classes.LuacraftItem;
import fr.luacraft.modloader.ILuaContainer;
import fr.luacraft.modloader.ILuaContainerObject;
import net.minecraft.creativetab.CreativeTabs;

public class LuaItem implements ILuaContainer
{
    private LuacraftItem item;

    public LuaItem(String id)
    {
        this.item = new LuacraftItem(id);
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

    public LuacraftItem getItem()
    {
        return item;
    }

    @Override
    public String getType()
    {
        return "Item";
    }

    @Override
    public ILuaContainerObject getContainedObject()
    {
        return getItem();
    }
}
