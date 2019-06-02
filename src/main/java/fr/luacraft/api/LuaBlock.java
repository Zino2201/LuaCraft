package fr.luacraft.api;

import fr.luacraft.modloader.ILuaObject;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;

/**
 * Represent a Block in lua
 * @author Zino
 */
public class LuaBlock implements ILuaObject
{
    private Block block;

    public LuaBlock(Block block)
    {
        this.block = block;
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

    public void SetLightLevel(float lightLevel)
    {
        block.setLightLevel(lightLevel);
    }

    public void SetResistance(float resistance)
    {
        block.setResistance(resistance);
    }

    public void SetLightOpacity(int opacity)
    {
        block.setLightOpacity(opacity);
    }

    public void SetUnbreakable()
    {
        block.setBlockUnbreakable();
    }

    public Block getBlock()
    {
        return block;
    }

    @Override
    public String GetType()
    {
        return "Block";
    }

    @Override
    public Object getObject()
    {
        return block;
    }
}
