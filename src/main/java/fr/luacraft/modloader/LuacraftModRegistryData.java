package fr.luacraft.modloader;

import fr.luacraft.api.IBlockContainerObject;
import fr.luacraft.api.IItemContainerObject;
import fr.luacraft.api.world.LuacraftOre;

import java.util.ArrayList;
import java.util.List;

public class LuacraftModRegistryData
{
    private List<LuacraftOre> ores;
    private List<IBlockContainerObject> blocks;
    private List<IItemContainerObject> items;

    public LuacraftModRegistryData()
    {
        this.ores = new ArrayList<LuacraftOre>();
        this.blocks = new ArrayList<IBlockContainerObject>();
        this.items = new ArrayList<IItemContainerObject>();
    }

    public void addOre(String id, int dimensionID,int minY, int maxY, int veinSize, int chances)
    {
        ores.add(new LuacraftOre(id, dimensionID, minY, maxY, veinSize, chances));
    }

    public void addBlock(IBlockContainerObject block)
    {
        blocks.add(block);
    }

    public void addItem(IItemContainerObject item)
    {
        items.add(item);
    }

    public List<LuacraftOre> getOres()
    {
        return ores;
    }
}
