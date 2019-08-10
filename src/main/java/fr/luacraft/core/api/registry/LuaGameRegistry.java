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
        /** Register recipe */
        ArrayList<Object> regParams = new ArrayList<Object>();
        for (String slot : slots)
        {
            if(slot != null)
            {
                if(slot.contains(":"))
                    regParams.add(LuaUtil.getItemStackFromStr(slot));
                else
                    regParams.add(slot);
            }
        }

        GameRegistry.addRecipe(new ShapelessOreRecipe(LuaUtil.getItemStackFromStr(output),
                regParams.toArray()));
    }

    /**
     * Add a shaped recipe
     * @param output
     * @param slots
     */
    public static void addShapedRecipe(String output, String[] slots)
    {
        // FIXME: I don't think this code is the most beautiful, optimized and easiest way ^^

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

        /** Register recipe */
        ArrayList<Object> regParams = new ArrayList<Object>(params);

        // Add to the params array the stacks associed to their characters
        for (Map.Entry<String, Character> entry : idChar.entrySet())
        {
            regParams.add(entry.getValue());
            if(entry.getKey().contains(":"))
                regParams.add(LuaUtil.getItemStackFromStr(entry.getKey()));
            else
                regParams.add(entry.getKey());
        }

        GameRegistry.addRecipe(new ShapedOreRecipe(LuaUtil.getItemStackFromStr(output),
                regParams.toArray()));
    }

    /**
     * Add a smelt
     * @param input
     * @param output
     * @param xp
     */
    public static void addSmelting(String input, String output, float xp)
    {
        GameRegistry.addSmelting(LuaUtil.getItemStackFromStr(input),
                LuaUtil.getItemStackFromStr(output), xp);
    }
}
