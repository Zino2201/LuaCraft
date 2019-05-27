package fr.luacraft.api;

import fr.luacraft.api.classes.LuacraftBlock;
import fr.luacraft.modloader.ILuaContainer;
import fr.luacraft.modloader.ILuaContainerObject;
import net.minecraft.creativetab.CreativeTabs;

/**
 * Represent a Block in lua
 */
public class LuaBlock implements ILuaContainer
{
    private LuacraftBlock block;

    public LuaBlock(String id)
    {
        this.block = new LuacraftBlock(id);
    }

    public void SetCreativeTab(String label)
    {
        for(CreativeTabs tab : CreativeTabs.creativeTabArray)
        {
            if (tab.getTabLabel().equals(label))
            {
                block.setCreativeTab(tab);
            }
        }
    }

    public void SetHardness(float hardness)
    {
        block.setHardness(hardness);
    }

    public LuacraftBlock getBlock()
    {
        return block;
    }

    @Override
    public String getType()
    {
        return "Block";
    }

    @Override
    public ILuaContainerObject getContainedObject()
    {
        return getBlock();
    }
}
