package fr.luacraft.core.api.blocks;

import com.naef.jnlua.util.LuaFunction;
import cpw.mods.fml.relauncher.Side;
import fr.luacraft.core.Luacraft;
import fr.luacraft.core.api.ILuaObject;
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

    @LuaFunction
    public void SetCreativeTab(String label)
    {
        if(Luacraft.getInstance().getProxy().getSide() == Side.CLIENT)
        {
            for (CreativeTabs tab : CreativeTabs.creativeTabArray)
            {
                if (tab.getTabLabel().equals(label))
                {
                    block.setCreativeTab(tab);
                }
            }
        }
    }

    @LuaFunction
    public void SetHardness(float hardness)
    {
        block.setHardness(hardness);
    }

    @LuaFunction
    public void SetLightLevel(float lightLevel)
    {
        block.setLightLevel(lightLevel);
    }

    @LuaFunction
    public void SetResistance(float resistance)
    {
        block.setResistance(resistance);
    }

    @LuaFunction
    public void SetLightOpacity(int opacity)
    {
        block.setLightOpacity(opacity);
    }

    @LuaFunction
    public void SetUnbreakable()
    {
        block.setBlockUnbreakable();
    }

    public Block getBlock()
    {
        return block;
    }

    @Override
    public boolean isContainer()
    {
        return true;
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
