package fr.luacraft.modloader;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.luacraft.api.classes.LuacraftBlock;
import fr.luacraft.api.classes.LuacraftItem;
import fr.luacraft.core.Luacraft;
import net.minecraft.item.ItemStack;

import java.util.*;

public class LuaGameRegistry
{
    private static List<RegistryRecipe> recipes = new ArrayList<RegistryRecipe>();
    private static List<RegistrySmelt> smelts = new ArrayList<RegistrySmelt>();

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
        recipes.add(new RegistryRecipe(true, output, slots, null));
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

        recipes.add(new RegistryRecipe(false, output, params.toArray(), idChar));
    }

    public static void addSmelting(String input, String output, float xp)
    {
        smelts.add(new RegistrySmelt(input, output, xp));
    }

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
                        params.add(recipeIDToItemStack(slot));
                }

                GameRegistry.addShapelessRecipe(recipeIDToItemStack(craft.getOutput()), params.toArray());
            }
            else
            {
                ArrayList<Object> params = new ArrayList<Object>(Arrays.asList(craft.getParams()));

                // Add to the params array the stacks associed to their characters
                for (Map.Entry<String, Character> entry : craft.getIdCharMap().entrySet())
                {
                    params.add(entry.getValue());
                    params.add(recipeIDToItemStack(entry.getKey()));
                }

                GameRegistry.addShapedRecipe(recipeIDToItemStack(craft.getOutput()), params.toArray());
            }
        }

        for(RegistrySmelt smelt : smelts)
        {
            GameRegistry.addSmelting(recipeIDToItemStack(smelt.getInput()), recipeIDToItemStack(smelt.getOutput()),
                    smelt.getExperienceGain());
        }
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
