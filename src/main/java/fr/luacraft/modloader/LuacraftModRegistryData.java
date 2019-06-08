package fr.luacraft.modloader;

import fr.luacraft.core.api.command.LuacraftCommand;
import fr.luacraft.core.api.world.LuacraftOre;
import net.minecraft.block.Block;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import java.util.HashSet;
import java.util.Set;

/**
 * Contains registry data about a mod
 */
public class LuacraftModRegistryData
{
    private Set<LuacraftOre> ores;
    private Set<Block> blocks;
    private Set<Item> items;
    private Set<CreativeTabs> creativeTabs;
    private Set<GuiScreen> guiScreens;
    private Set<LuacraftCommand> serverCommands;

    public LuacraftModRegistryData()
    {
        this.ores = new HashSet<LuacraftOre>();
        this.blocks = new HashSet<Block>();
        this.items = new HashSet<Item>();
        this.creativeTabs = new HashSet<CreativeTabs>();
        this.guiScreens = new HashSet<GuiScreen>();
        this.serverCommands = new HashSet<LuacraftCommand>();
    }

    /**
     * Add a gui
     * @param screen
     */
    public void addGui(GuiScreen screen)
    {
        guiScreens.add(screen);
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
     * Add a server command
     * @param command
     */
    public void addServerCommand(LuacraftCommand command)
    {
        serverCommands.add(command);
    }

    /**
     * Get a block by its ID
     * @param id
     * @return
     */
    public Block getBlockByID(String id)
    {
        for(Block block : blocks)
        {
            if (block.getUnlocalizedName().equals(id))
                return block;
        }

        return null;
    }

    public Item getItemByID(String id)
    {
        for(Item item : items)
        {
            if(item.getUnlocalizedName().equals(id))
                return item;
        }

        return null;
    }

    /**
     * Return true if mod contains specified block otherwise false
     * @param block
     * @return
     */
    public boolean hasBlock(Block block)
    {
        return blocks.contains(block);
    }

    /**
     * Return true if mod contains specified item otherwise false
     * @param item
     * @return
     */
    public boolean hasItem(Item item)
    {
        return items.contains(item);
    }

    /**
     * Get all ores
     * @return
     */
    public Set<LuacraftOre> getOres()
    {
        return ores;
    }

    /**
     * Get all server commands
     * @return
     */
    public Set<LuacraftCommand> getServerCommands()
    {
        return serverCommands;
    }
}
