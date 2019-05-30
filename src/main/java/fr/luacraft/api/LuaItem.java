package fr.luacraft.api;

import fr.luacraft.modloader.ILuaContainer;
import fr.luacraft.modloader.ILuaContainerObject;
import net.minecraft.creativetab.CreativeTabs;

public class LuaItem implements ILuaContainer
{
    private IItemContainerObject item;

    public LuaItem(IItemContainerObject item)
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

    public IItemContainerObject getItem()
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
