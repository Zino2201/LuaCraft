package fr.luacraft.core.api.hooks;

import fr.luacraft.core.api.entity.LuaEntityPlayer;
import fr.luacraft.core.api.world.LuaWorld;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

/**
 * Shared block hooks
 * @author Zino
 */
public class LuacraftBlockHooks
{
    public static boolean onBlockActivated(Block block, World world, int x, int y, int z, EntityPlayer player,
                                    int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
    {
        Boolean bool = LuaHookManager.call(
                Boolean.class,
                block,
                "OnBlockActivated",
                new LuaWorld(world),
                x,
                y,
                z,
                new LuaEntityPlayer(player),
                p_149727_6_,
                p_149727_7_,
                p_149727_8_,
                p_149727_9_);

        return bool == null ? false : bool;
    }

    public static int onBlockPlaced(Block block, World world, int x, int y, int z,
                             int side, float hitX, float hitY, float hitZ, int metadata)
    {
        Integer md = LuaHookManager.call(
                Integer.class,
                block,
                "OnBlockPlaced",
                new LuaWorld(world),
                x,
                y,
                z,
                side,
                hitX,
                hitY,
                hitZ,
                metadata);

        return md == null ? metadata : md;
    }

    public static void onBlockClicked(Block block, World world, int x, int y, int z, EntityPlayer player)
    {
        LuaHookManager.call(
                block,
                "OnBlockClicked",
                new LuaWorld(world),
                x,
                y,
                z,
                new LuaEntityPlayer(player));
    }

    public static boolean onBlockEventReceived(Block block, World world, int x, int y, int z, int side, int metadata)
    {
        Boolean bool = LuaHookManager.call(
                Boolean.class,
                block,
                "OnBlockEventReceieved",
                new LuaWorld(world),
                x,
                y,
                z,
                side,
                metadata
        );

        return bool == null ? false : bool;
    }

    public static void onBlockAdded(Block block, World world, int x, int y, int z)
    {
        LuaHookManager.call(
                block,
                "OnBlockAdded",
                new LuaWorld(world),
                x,
                y,
                z);
    }

    public static void onBlockDestroyedByExplosion(Block block, World world, int x, int y, int z,
                                                   Explosion explosion)
    {

    }
}