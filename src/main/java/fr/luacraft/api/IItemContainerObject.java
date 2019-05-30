package fr.luacraft.api;

import fr.luacraft.modloader.ILuaContainerObject;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public interface IItemContainerObject extends ILuaContainerObject
{
    Item setCreativeTab(CreativeTabs tab);
}
