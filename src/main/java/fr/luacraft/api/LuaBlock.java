package fr.luacraft.api;

import fr.luacraft.classes.LuacraftBlock;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;

/**
 * Represent a Block in lua
 */
public class LuaBlock extends LuaObject
{
    public Block block;

    public LuaBlock(String id)
    {
        super(id);

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

    public Block getBlock()
    {
        return block;
    }

    @Override
    public String getTypeName()
    {
        return "Block";
    }

    @Override
    public Object getContainedObject()
    {
        return getBlock();
    }
}
