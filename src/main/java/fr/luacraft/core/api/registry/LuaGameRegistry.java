package fr.luacraft.core.api.registry;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.luacraft.core.Luacraft;
import fr.luacraft.util.LuaUtil;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import java.util.*;

/**
 * Contains functions for register lua objects
 * @author Zino
 */
public class LuaGameRegistry
{
    private static List<RegistryRecipe> recipes = new ArrayList<RegistryRecipe>();
    private static List<RegistrySmelt> smelts = new ArrayList<RegistrySmelt>();

    /**
     * Register a block
     * @param id
     * @param block
     */
    public static void registerBlock(String id, Block block)
    {
        GameRegistry.registerBlock(block, id);
        Luacraft.getInstance().getProxy().getCurrentMod().getRegistryData().addBlock(block);
    }

    /**
     * Register a item
     * @param id
     * @param item
     */
    public static void registerItem(String id, Item item)
    {
        GameRegistry.registerItem(item, id);
        Luacraft.getInstance().getProxy().getCurrentMod().getRegistryData().addItem(item);
    }

    /**
     * Add a recipe
     * @param output
     * @param shapeless
     * @param slots
     */
    public static void addRecipe(String output, boolean shapeless, String[] slots)
    {
        if(shapeless)
            addShapelessRecipe(output, slots);
        else
            addShapedRecipe(output, slots);
    }

    /**
     * Add a shapeless recipe
     * @param output
     * @param slots
     */
    public static void addShapelessRecipe(String output, String[] slots)
    {
        recipes.add(new RegistryRecipe(true, output, slots, null));
    }

    /**
     * Add a shaped recipe
     * @param output
     * @param slots
     */
    public static void addShapedRecipe(String output, String[] slots)
    {
        char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUWXYZ".toCharArray();

        // Associate a id with a character
        Set<String> cleanedSlotsSet = new HashSet<String>(slots.length);
        cleanedSlotsSet.addAll(Arrays.asList(slots));

        HashMap<String, Character> idChar = new HashMap<String, Character>(cleanedSlotsSet.size());
        int aIndex = 0;
        for(String slot : cleanedSlotsSet)
        {
            if(slot != null)
                idChar.put(slot, alphabet[aIndex++]);
        }

        // Fill the params array

        int lineCount = 0;
        for(int i = 0; i < slots.length / 3; i++)
        {
            String[] line = new String[] {slots[i], slots[i + 1], slots[i + 2]};
            int numNull = 0;
            for (String str : line)
            {
                if(str == null)
                    numNull++;
            }
            if(numNull < 3)
                lineCount++;
        }
        ArrayList<StringBuilder> lines = new ArrayList<StringBuilder>(lineCount);
        for(int i = 0; i < lineCount; i++)
            lines.add(new StringBuilder());

        int currentLine = 0;
        int written = 0;

        for(int i = 0; i < slots.length; i++)
        {
            if(slots[i] != null)
                lines.get(currentLine).append(idChar.get(slots[i]));
            else
                lines.get(currentLine).append(" ");
            written++;
            if(written >= 3)
            {
                currentLine++;
                written = 0;
                if(currentLine == lineCount)
                {
                    break;
                }
            }
        }

        ArrayList<Object> params = new ArrayList<Object>(lines.size());
        for(StringBuilder str : lines)
        {
            params.add(str.toString());
        }

        recipes.add(new RegistryRecipe(false, output, params.toArray(), idChar));
    }

    /**
     * Add a smelt
     * @param input
     * @param output
     * @param xp
     */
    public static void addSmelting(String input, String output, float xp)
    {
        smelts.add(new RegistrySmelt(input, output, xp));
    }

    /**
     * Register all craft and smelts
     */
    public static void registerCraftAndSmelts()
    {
        for(RegistryRecipe craft : recipes)
        {
            if(craft.isShapeless())
            {
                ArrayList<Object> params = new ArrayList<Object>();
                for (String slot : (String[]) craft.getParams())
                {
                    if(slot != null)
                    {
                        if(slot.contains(":"))
                            params.add(LuaUtil.getItemStackFromID(slot));
                        else
                            params.add(slot);
                    }
                }

                GameRegistry.addRecipe(new ShapelessOreRecipe(LuaUtil.getItemStackFromID(craft.getOutput()), params.toArray()));
            }
            else
            {
                ArrayList<Object> params = new ArrayList<Object>(Arrays.asList(craft.getParams()));

                // Add to the params array the stacks associed to their characters
                for (Map.Entry<String, Character> entry : craft.getIdCharMap().entrySet())
                {
                    params.add(entry.getValue());
                    if(entry.getKey().contains(":"))
                        params.add(LuaUtil.getItemStackFromID(entry.getKey()));
                    else
                        params.add(entry.getKey());
                }

                GameRegistry.addRecipe(new ShapedOreRecipe(LuaUtil.getItemStackFromID(craft.getOutput()), params.toArray()));
            }
        }

        for(RegistrySmelt smelt : smelts)
        {
            GameRegistry.addSmelting(LuaUtil.getItemStackFromID(smelt.getInput()), LuaUtil.getItemStackFromID(smelt.getOutput()),
                    smelt.getExperienceGain());
        }
    }
}
