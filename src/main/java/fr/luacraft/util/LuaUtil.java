package fr.luacraft.util;

import com.naef.jnlua.LuaState;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.ForgeDirection;

import java.io.*;

/**
 * Lua utils functions
 * @author Zino
 */
// TODO: Move script related things to another class
public class LuaUtil
{
    /**
     * Currently running script
     */
    private static File runningScript;

    /**
     * Run lua code from a file
     * @param l
     * @param file
     */
    public static void runFromFile(LuaState l, File file)
    {
        try
        {
            runningScript = file;
            FileInputStream in = new FileInputStream(file);
            l.load(in, file.getPath(), "t");
            l.call(0, 0);
            in.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Run lua code from input stream
     * @param l
     * @param file
     * @param inputStream
     */
    public static void runFromFile(LuaState l, File file, InputStream inputStream)
    {
        try
        {
            runningScript = file;
            l.load(inputStream, file.getPath(), "t");
            l.call(0, 0);
            inputStream.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Get running script
     * @return
     */
    public static File getRunningScript()
    {
        return runningScript;
    }

    /**
     * Get a item stack by its ID (modid:name:meta:count)
     * @param itemstackID
     * @return
     */
    public static ItemStack getItemStackFromID(String itemstackID)
    {
        // TODO: Switch meta & count

        String[] formattedID = itemstackID.split(":");
        String modid = formattedID[0];
        String id = formattedID[1];
        String stackSize = "1";
        if(formattedID.length > 2 && formattedID[2] != null)
            stackSize = formattedID[2];
        ItemStack stack = GameRegistry.findItemStack(modid, id, Integer.parseInt(stackSize));
        return stack;
    }
}
