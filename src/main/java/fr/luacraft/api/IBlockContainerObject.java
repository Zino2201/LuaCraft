package fr.luacraft.api;

import fr.luacraft.modloader.ILuaContainerObject;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;

public interface IBlockContainerObject extends ILuaContainerObject
{
    Block setCreativeTab(CreativeTabs label);
    Block setHardness(float hardness);
    Block getBlock();
}
