package fr.luacraft.util;

import net.minecraft.block.material.Material;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Utils for mc enums
 * @author Zino
 */
public class EnumUtil
{
    /**
     * Get a material by its ID
     * @param id
     * @return
     */
    public static Material getMaterialFromInt(int id)
    {
        switch(id) {
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

    public static int getCreatureTypeAsInt(EnumCreatureType type)
    {
        switch(type)
        {
            default:
            case ambient:
                return 0;
            case monster:
                return 1;
            case creature:
                return 2;
            case waterCreature:
                return 3;
        }
    }

    public static EnumCreatureType getCreatureTypeFromInt(int type)
    {
        switch(type)
        {
            default:
            case 0:
                return EnumCreatureType.ambient;
            case 1:
                return EnumCreatureType.monster;
            case 2:
                return EnumCreatureType.creature;
            case 3:
                return EnumCreatureType.waterCreature;
        }
    }

    public static int getForgeDirectionAsInt(ForgeDirection dir)
    {
        switch(dir)
        {
            default:
            case UP:
                return 0;
            case DOWN:
                return 1;
            case NORTH:
                return 2;
            case SOUTH:
                return 3;
            case WEST:
                return 4;
            case EAST:
                return 5;
        }
    }

    public static int getRarityAsInt(EnumRarity rarity)
    {
        switch (rarity)
        {
            default:
            case common:
                return 0;
            case uncommon:
                return 1;
            case rare:
                return 2;
            case epic:
                return 3;
        }
    }

    public static int getActionAsInt(EnumAction action)
    {
        switch (action)
        {
            default:
            case none:
                return 0;
            case eat:
                return 1;
            case drink:
                return 2;
            case block:
                return 3;
            case bow:
                return 4;
        }
    }
}
