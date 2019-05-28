package fr.luacraft.modloader;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.luacraft.api.classes.LuacraftBlock;
import fr.luacraft.api.classes.LuacraftItem;
import fr.luacraft.core.Luacraft;
import net.minecraft.item.ItemStack;

import java.util.*;

public class LuaGameRegistry
{
    public static void register(String id, ILuaContainerObject object)
    {
        switch(object.getType())
        {
            case BLOCK:
                LuacraftBlock block = (LuacraftBlock) object;
                GameRegistry.registerBlock(block, id);
                Luacraft.getInstance().getProxy().getCurrentMod().getRegistryData().addBlock(block);
                break;
            case ITEM:
                LuacraftItem item = (LuacraftItem) object;
                GameRegistry.registerItem(item, id);
                Luacraft.getInstance().getProxy().getCurrentMod().getRegistryData().addItem(item);
                break;
        }
    }

    public static void addRecipe(String output, boolean shapeless, String[] slots)
    {
        if(shapeless)
            addShapelessRecipe(output, slots);
        else
            addShapedRecipe(output, slots);
    }

    public static void addShapelessRecipe(String output, String[] slots)
    {
        ItemStack outputStack = recipeIDToItemStack(output);

        ArrayList<Object> params = new ArrayList<Object>();
        for (String slot : slots)
        {
            if(slot != null)
                params.add(recipeIDToItemStack(slot));
        }

        GameRegistry.addShapelessRecipe(outputStack, params.toArray());
    }

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
        // Add to the params array the stacks associed to their characters
        for (Map.Entry<String, Character> entry : idChar.entrySet())
        {
            params.add(entry.getValue());
            params.add(recipeIDToItemStack(entry.getKey()));
        }

        ItemStack outputStack = recipeIDToItemStack(output);

        GameRegistry.addShapedRecipe(outputStack, params.toArray());
    }

    private static ItemStack recipeIDToItemStack(String recipeID)
    {
        String[] formattedID = recipeID.split(":");
        String modid = formattedID[0];
        String id = formattedID[1];
        String stackSize = "1";
        if(formattedID.length > 2 && formattedID[2] != null)
            stackSize = formattedID[2];
        ItemStack stack = GameRegistry.findItemStack(modid, id, Integer.parseInt(stackSize));
        return stack;
    }
}
