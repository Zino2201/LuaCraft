package fr.luacraft.util;

import com.naef.jnlua.LuaState;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;

import java.io.*;

/**
 * Lua utils functions
 * @author Zino
 */
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
     * Get a material by its ID
     * @param id
     * @return
     */
    public static Material getMaterialByID(int id)
    {
        switch(id) {
            case 0:
                return Material.air;
            case 1:
                return Material.grass;
            case 2:
                return Material.ground;
            case 3:
                return Material.wood;
            case 4:
                return Material.rock;
            case 5:
                return Material.iron;
            case 6:
                return Material.anvil;
            case 7:
                return Material.water;
            case 8:
                return Material.lava;
            case 9:
                return Material.leaves;
            case 10:
                return Material.plants;
            case 11:
                return Material.vine;
            case 12:
                return Material.sponge;
            case 13:
                return Material.cloth;
            case 14:
                return Material.fire;
            case 15:
                return Material.sand;
            case 16:
                return Material.circuits;
            case 17:
                return Material.carpet;
            case 18:
                return Material.glass;
            case 19:
                return Material.redstoneLight;
            case 20:
                return Material.tnt;
            case 21:
                return Material.coral;
            case 22:
                return Material.ice;
            case 23:
                return Material.packedIce;
            case 24:
                return Material.snow;
            case 25:
                return Material.craftedSnow;
            case 26:
                return Material.cactus;
            case 27:
                return Material.clay;
            case 28:
                return Material.gourd;
            case 29:
                return Material.dragonEgg;
            case 30:
                return Material.portal;
            case 31:
                return Material.cake;
            case 32:
                return Material.web;
            default:
                return Material.rock;
        }
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
