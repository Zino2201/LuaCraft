package fr.luacraft.modloader;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.luacraft.api.world.LuacraftOre;
import fr.luacraft.core.Luacraft;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains registry data about a mod
 */
public class LuacraftModRegistryData
{
    private List<LuacraftOre> ores;
    private List<Block> blocks;
    private List<Item> items;
    private List<CreativeTabs> creativeTabs;

    public LuacraftModRegistryData()
    {
        this.ores = new ArrayList<LuacraftOre>();
        this.blocks = new ArrayList<Block>();
        this.items = new ArrayList<Item>();
        this.creativeTabs = new ArrayList<CreativeTabs>();
    }

    /**
     * Add a ore
     * @param id
     * @param dimensionID
     * @param minY
     * @param maxY
     * @param veinSize
     * @param chances
     */
    public void addOre(String id, int dimensionID, int minY, int maxY, int veinSize, int chances)
    {
        ores.add(new LuacraftOre(id, dimensionID, minY, maxY, veinSize, chances));
    }

    /**
     * Add a block
     * @param block
     */
    public void addBlock(Block block)
    {
        blocks.add(block);
    }

    /**
     * Add a item
     * @param item
     */
    public void addItem(Item item)
    {
        items.add(item);
    }

    /**
     * Add a creative tab
     * @param creativeTab
     */
    public void addCreativeTab(CreativeTabs creativeTab)
    {
        creativeTabs.add(creativeTab);
    }

    /**
     * Get a block by its ID
     * @param id
     * @return
     */
    public Block getBlockByID(String id)
    {
        return GameRegistry.findBlock(Luacraft.getInstance().getProxy().getCurrentMod().getModId(), id);
    }

    /**
     * Get all ores
     * @return
     */
    public List<LuacraftOre> getOres()
    {
        return ores;
    }
}
