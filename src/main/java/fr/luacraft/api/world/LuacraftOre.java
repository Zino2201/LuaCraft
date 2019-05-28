package fr.luacraft.api.world;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;

public class LuacraftOre
{
    private Block block;
    private int dimensionID;
    private int minY;
    private int maxY;
    private int veinSize;
    private int chances;

    public LuacraftOre(String id, int dimensionID, int minY, int maxY, int veinSize, int chances)
    {
        String[] formattedID = id.split(":");

        this.block = GameRegistry.findBlock(formattedID[0], formattedID[1]);
        this.dimensionID = dimensionID;
        this.minY = minY;
        this.maxY = maxY;
        this.veinSize = veinSize;
        this.chances = chances;
    }

    public Block getBlock()
    {
        return block;
    }

    public int getMinY()
    {
        return minY;
    }

    public int getMaxY()
    {
        return maxY;
    }

    public int getVeinSize()
    {
        return veinSize;
    }

    public int getChances()
    {
        return chances;
    }

    public int getDimensionID()
    {
        return dimensionID;
    }
}
