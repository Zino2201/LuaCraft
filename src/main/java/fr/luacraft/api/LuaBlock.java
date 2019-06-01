package fr.luacraft.api;

import fr.luacraft.modloader.ILuaObject;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

/**
 * Represent a Block in lua
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

    public static Material getMaterialFromID(int id)
    {
        switch(id)
        {
            case 0:
                return Material.air;
            case 1:
                return Material.grass;
            case 2:
                return Material.ground;
            case 3:
                return Material.wood;
            case 4:
                return Material.rock;
            case 5:
                return Material.iron;
            case 6:
                return Material.anvil;
            case 7:
                return Material.water;
            case 8:
                return Material.lava;
            case 9:
                return Material.leaves;
            case 10:
                return Material.plants;
            case 11:
                return Material.vine;
            case 12:
                return Material.sponge;
            case 13:
                return Material.cloth;
            case 14:
                return Material.fire;
            case 15:
                return Material.sand;
            case 16:
                return Material.circuits;
            case 17:
                return Material.carpet;
            case 18:
                return Material.glass;
            case 19:
                return Material.redstoneLight;
            case 20:
                return Material.tnt;
            case 21:
                return Material.coral;
            case 22:
                return Material.ice;
            case 23:
                return Material.packedIce;
            case 24:
                return Material.snow;
            case 25:
                return Material.craftedSnow;
            case 26:
                return Material.cactus;
            case 27:
                return Material.clay;
            case 28:
                return Material.gourd;
            case 29:
                return Material.dragonEgg;
            case 30:
                return Material.portal;
            case 31:
                return Material.cake;
            case 32:
                return Material.web;
            default:
                return Material.rock;
        }
    }
}
