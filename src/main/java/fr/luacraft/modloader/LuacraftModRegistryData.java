package fr.luacraft.modloader;

import fr.luacraft.api.world.LuacraftOre;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class LuacraftModRegistryData
{
    private List<LuacraftOre> ores;
    private List<Block> blocks;
    private List<Item> items;

    public LuacraftModRegistryData()
    {
        this.ores = new ArrayList<LuacraftOre>();
        this.blocks = new ArrayList<Block>();
        this.items = new ArrayList<Item>();
    }

    public void addOre(String id, int dimensionID, int minY, int maxY, int veinSize, int chances)
    {
        ores.add(new LuacraftOre(id, dimensionID, minY, maxY, veinSize, chances));
    }

    public void addBlock(Block block)
    {
        blocks.add(block);
    }

    public void addItem(Item item)
    {
        items.add(item);
    }

    public List<LuacraftOre> getOres()
    {
        return ores;
    }
}
