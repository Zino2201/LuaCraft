package fr.luacraft.modloader;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.luacraft.core.Luacraft;
import fr.luacraft.core.api.command.LuacraftCommand;
import fr.luacraft.core.api.world.LuacraftOre;
import net.minecraft.block.Block;
import net.minecraft.client.gui.GuiScreen;
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
    private List<GuiScreen> guiScreens;
    private List<LuacraftCommand> serverCommands;

    public LuacraftModRegistryData()
    {
        this.ores = new ArrayList<LuacraftOre>();
        this.blocks = new ArrayList<Block>();
        this.items = new ArrayList<Item>();
        this.creativeTabs = new ArrayList<CreativeTabs>();
        this.guiScreens = new ArrayList<GuiScreen>();
        this.serverCommands = new ArrayList<LuacraftCommand>();
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

    /**
     * Get all server commands
     * @return
     */
    public List<LuacraftCommand> getServerCommands()
    {
        return serverCommands;
    }
}
