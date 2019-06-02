package fr.luacraft.api.world;

import cpw.mods.fml.common.IWorldGenerator;
import fr.luacraft.modloader.LuacraftMod;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;

import java.util.Random;

/**
 * Luacraft custom world generator implementation compatible with Lua ores
 * @author Zino
 */
public class LuacraftWorldGen implements IWorldGenerator
{
    private LuacraftMod mod;

    public LuacraftWorldGen(LuacraftMod mod)
    {
        this.mod = mod;
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
    {
        switch(world.provider.dimensionId)
        {
            case -1: // Nether

                break;
            case 0: // Overworld
                generateOverworld(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider);
                break;
            case 1: // End
                break;
            default:
                break;
        }
    }

    /**
     * Generate overworld ores
     * @param random
     * @param chunkX
     * @param chunkZ
     * @param world
     * @param chunkGenerator
     * @param chunkProvider
     */
    private void generateOverworld(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
    {
        for(LuacraftOre ore : mod.getRegistryData().getOres())
        {
            if(ore.getDimensionID() == 0)
                generateOre(ore.getBlock(), world, random, chunkX * 16, chunkZ * 16,
                        ore.getMinY(), ore.getMaxY(), ore.getVeinSize(), ore.getChances());
        }
    }

    /**
     * Generate a ore
     * @param ore
     * @param world
     * @param random
     * @param x
     * @param z
     * @param minY
     * @param maxY
     * @param size
     * @param chances
     */
    private void generateOre(Block ore, World world, Random random, int x, int z, int minY, int maxY, int size, int chances)
    {
        int deltaY = maxY - minY;

        for (int i = 0; i < chances; i++)
        {
            int gX = x + random.nextInt(16);
            int gY = minY + random.nextInt(deltaY);
            int gZ = z + random.nextInt(16);

            WorldGenMinable generator = new WorldGenMinable(ore, size);
            generator.generate(world, random, gX, gY, gZ);
        }
    }
}
