package fr.luacraft.modloader;

import fr.luacraft.api.classes.LuacraftBlock;
import fr.luacraft.api.classes.LuacraftItem;
import fr.luacraft.api.world.LuacraftOre;

import java.util.ArrayList;
import java.util.List;

public class LuacraftModRegistryData
{
    private List<LuacraftOre> ores;
    private List<LuacraftBlock> blocks;
    private List<LuacraftItem> items;

    public LuacraftModRegistryData()
    {
        this.ores = new ArrayList<LuacraftOre>();
        this.blocks = new ArrayList<LuacraftBlock>();
        this.items = new ArrayList<LuacraftItem>();
    }

    public void addOre(String id, int dimensionID,int minY, int maxY, int veinSize, int chances)
    {
        ores.add(new LuacraftOre(id, dimensionID, minY, maxY, veinSize, chances));
    }

    public void addBlock(LuacraftBlock block)
    {
        blocks.add(block);
    }

    public void addItem(LuacraftItem item)
    {
        items.add(item);
    }

    public List<LuacraftOre> getOres()
    {
        return ores;
    }
}
