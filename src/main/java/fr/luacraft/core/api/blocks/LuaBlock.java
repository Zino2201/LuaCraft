package fr.luacraft.core.api.blocks;

import com.naef.jnlua.util.LuaFunction;
import fr.luacraft.core.api.*;
import fr.luacraft.core.api.creativetab.LuaCreativeTab;
import fr.luacraft.core.api.entity.LuaEntityPlayer;
import fr.luacraft.core.api.items.LuaItem;
import fr.luacraft.core.api.util.LuaChunkCoordinates;
import fr.luacraft.core.api.util.LuaIIcon;
import fr.luacraft.core.api.world.LuaExplosion;
import fr.luacraft.core.api.world.LuaIBlockAccess;
import fr.luacraft.core.api.world.LuaWorld;
import fr.luacraft.util.EnumUtil;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;

/**
 * Represent a Block in lua
 * @author Zino
 */
@SuppressWarnings("unused")
public class LuaBlock implements ILuaObject
{
    private Block block;

    public LuaBlock(Block block)
    {
        this.block = block;
    }

    /**
     * Set creative tab
     * @param tab
     */
    @LuaFunction
    public void SetCreativeTab(LuaCreativeTab tab)
    {
        block.setCreativeTab(tab.getCreativeTabs());
    }

    /**
     * Set block hardness
     * @param hardness
     */
    @LuaFunction
    public void SetHardness(float hardness)
    {
        block.setHardness(hardness);
    }

    /**
     * Set block light level
     * @param lightLevel
     */
    @LuaFunction
    public void SetLightLevel(float lightLevel)
    {
        block.setLightLevel(lightLevel);
    }

    /**
     * Set block resistance
     * @param resistance
     */
    @LuaFunction
    public void SetResistance(float resistance)
    {
        block.setResistance(resistance);
    }

    /**
     * Set block light opacity
     * @param opacity
     */
    @LuaFunction
    public void SetLightOpacity(int opacity)
    {
        block.setLightOpacity(opacity);
    }

    /**
     * Set block to be unbreakable
     */
    @LuaFunction
    public void SetUnbreakable()
    {
        block.setBlockUnbreakable();
    }

    /**
     * Set block bounds
     * @param minX
     * @param minY
     * @param minZ
     * @param maxX
     * @param maxY
     * @param maxZ
     */
    @LuaFunction
    public void SetBlockBounds(float minX, float minY, float minZ, float maxX, float maxY, float maxZ)
    {
        block.setBlockBounds(minX, minY, minZ, maxX, maxY, maxZ);
    }

    /**
     * Set if block should tick randomly
     * @param tickRandomy
     */
    @LuaFunction
    public void SetTickRandomly(boolean tickRandomy)
    {
        block.setTickRandomly(tickRandomy);
    }

    /**
     * Get block opacity
     * @return
     */
    @LuaFunction
    public int GetLightOpacity()
    {
        return block.getLightOpacity();
    }

    /**
     * Get block light value
     * @return
     */
    @LuaFunction
    public int GetLightValue()
    {
        return block.getLightValue();
    }

    /**
     * Get block render pass
     * @return
     */
    @LuaFunction
    public int GetRenderBlockPass()
    {
        return block.getRenderBlockPass();
    }

    /**
     * Return if block provide redstone power
     * @return
     */
    @LuaFunction
    public boolean CanProvidePower()
    {
        return block.canProvidePower();
    }

    /**
     * Get harvest level
     * @param metadata
     * @return
     */
    @LuaFunction
    public int GetHarvestLevel(int metadata)
    {
        return block.getHarvestLevel(metadata);
    }

    /**
     * Get block bounds minX
     * @return
     */
    @LuaFunction
    public double GetBlockBoundsMinX()
    {
        return block.getBlockBoundsMinX();
    }

    /**
     * Get block bounds minY
     * @return
     */
    @LuaFunction
    public double GetBlockBoundsMinY()
    {
        return block.getBlockBoundsMinY();
    }

    /**
     * Get block bounds minZ
     * @return
     */
    @LuaFunction
    public double GetBlockBoundsMinZ()
    {
        return block.getBlockBoundsMinZ();
    }

    /**
     * Get block bounds maxX
     * @return
     */
    @LuaFunction
    public double GetBlockBoundsMaxX()
    {
        return block.getBlockBoundsMaxX();
    }

    /**
     * Get block bounds maxY
     * @return
     */
    @LuaFunction
    public double GetBlockBoundsMaxY()
    {
        return block.getBlockBoundsMaxY();
    }

    /**
     * Get block bounds maxZ
     * @return
     */
    @LuaFunction
    public double GetBlockBoundsMaxZ()
    {
        return block.getBlockBoundsMaxZ();
    }

    /**
     * Get block material
     * @return
     */
    @LuaFunction
    public LuaMaterial GetMaterial()
    {
        return new LuaMaterial(block.getMaterial());
    }

    /**
     * Get block ambient occlusion light value
     * @return
     */
    @LuaFunction
    public float GetAmbientOcclusionLightValue()
    {
        return block.getAmbientOcclusionLightValue();
    }

    /**
     * Get bed direction
     * @param world
     * @param x
     * @param y
     * @param z
     * @return
     */
    @LuaFunction
    public int GetBedDirection(LuaIBlockAccess world, int x, int y, int z)
    {
        return block.getBedDirection(world.getBlockAccess(), x, y, z);
    }

    /**
     * Get bed spawn position
     * @param world
     * @param x
     * @param y
     * @param z
     * @param player
     * @return
     */
    @LuaFunction
    public LuaChunkCoordinates GetBedSpawnPosition(LuaIBlockAccess world, int x, int y, int z,
                                                   LuaEntityPlayer player)
    {
        return new LuaChunkCoordinates(block.getBedSpawnPosition(world.getBlockAccess(), x, y, z,
                player.getEntityPlayer()));
    }

    /**
     * Get block texture from specified side
     * @param side
     * @return
     */
    @LuaFunction
    public LuaIIcon GetBlockTextureFromSide(int side)
    {
        return new LuaIIcon(block.getBlockTextureFromSide(side));
    }

    /**
     * Get block localized name
     * @return
     */
    @LuaFunction
    public String GetLocalizedName()
    {
        return block.getLocalizedName();
    }

    /**
     * Get block unlocalized name
     * @return
     */
    @LuaFunction
    public String GetUnlocalizedName()
    {
        return block.getUnlocalizedName();
    }

    /**
     * Get item icon name
     * @return
     */
    @LuaFunction
    public String GetItemIconName()
    {
        return block.getItemIconName();
    }

    /**
     * Get block experience drop
     * @param world
     * @param metadata
     * @param fortune
     * @return
     */
    @LuaFunction
    public int GetExpDrop(LuaIBlockAccess world, int metadata, int fortune)
    {
        return block.getExpDrop(world.getBlockAccess(), metadata, fortune);
    }

    /**
     * Get item
     * @param world
     * @param x
     * @param y
     * @param z
     * @return
     */
    @LuaFunction
    public LuaItem GetItem(LuaWorld world, int x, int y, int z)
    {
        return new LuaItem(block.getItem(world.getWorld(), x, y, z));
    }

    /**
     * Get block creative tab where it can be found
     * @return
     */
    @LuaFunction
    public LuaCreativeTab GetCreativeTabToDisplayOn()
    {
        return new LuaCreativeTab(block.getCreativeTabToDisplayOn());
    }

    /**
     * Get block explosion resistance
     * @param entity
     * @return
     */
    @LuaFunction
    public float GetExplosionResistance(Entity entity)
    {
        return block.getExplosionResistance(entity);
    }

    /**
     * Get fire spread speed
     * @return
     */
    @LuaFunction
    public int GetFireSpreadSpeed()
    {
        return Blocks.fire.getEncouragement(block);
    }

    /**
     * Get player relative block hardness
     * @param world
     * @param player
     * @param x
     * @param y
     * @param z
     * @return
     */
    @LuaFunction
    public float GetPlayerRelativeBlockHardness(LuaWorld world, LuaEntityPlayer player, int x, int y, int z)
    {
        return block.getPlayerRelativeBlockHardness(player.getEntityPlayer(), world.getWorld(),
                x, y, z);
    }

    /**
     * Get block render type
     * @return
     */
    @LuaFunction
    public int GetRenderType()
    {
        return block.getRenderType();
    }

    /**
     * Get block hardness
     * @return
     */
    @LuaFunction
    public float GetBlockHardness()
    {
        return block.getBlockHardness(null, 0, 0 , 0);
    }

    /**
     * Get if block can move
     * @return
     */
    @LuaFunction
    public boolean GetBlocksMovements()
    {
        return block.getBlocksMovement(null, 0, 0, 0);
    }

    //TODO:IS

    /**
     * Return if blocked can be placed at specified coordinates
     * @param world
     * @param x
     * @param y
     * @param z
     * @return
     */
    @LuaFunction
    public boolean CanPlaceBlockAt(LuaWorld world, int x, int y, int z)
    {
        return block.canPlaceBlockAt(world.getWorld(), x, y, z);
    }

    /**
     * Return true if block can stay at specified coordinates
     * @param world
     * @param x
     * @param y
     * @param z
     * @return
     */
    @LuaFunction
    public boolean CanBlockStay(LuaWorld world, int x, int y, int z)
    {
        return block.canBlockStay(world.getWorld(), x, y, z);
    }

    /**
     * Return true if block can connect redstone
     * @param access
     * @param x
     * @param y
     * @param z
     * @param side
     * @return
     */
    @LuaFunction
    public boolean CanConnectRedstone(LuaIBlockAccess access, int x, int y, int z, int side)
    {
        return block.canConnectRedstone(access.getBlockAccess(), x, y, z, side);
    }

    /**
     * Return true if specified creature type can spawn at specified coordinates
     * @param creatureType
     * @param world
     * @param x
     * @param y
     * @param z
     * @return
     */
    @LuaFunction
    public boolean CanCreatureSpawn(int creatureType, LuaIBlockAccess world, int x, int y, int z)
    {
        return block.canCreatureSpawn(EnumUtil.getCreatureTypeFromInt(creatureType),
                world.getBlockAccess(), x, y, z);
    }

    /**
     * Return true if block can be replaced by leaves
     * @param world
     * @param x
     * @param y
     * @param z
     * @return
     */
    @LuaFunction
    public boolean CanBeReplacedByLeaves(LuaIBlockAccess world, int x, int y, int z)
    {
        return block.canBeReplacedByLeaves(world.getBlockAccess(), x, y, z);
    }

    /**
     * Return true if block can drop from specified explosion
     * @param explosion
     * @return
     */
    @LuaFunction
    public boolean CanDropFromExplosion(LuaExplosion explosion)
    {
        return block.canDropFromExplosion(explosion.getExplosion());
    }

    /**
     * Return true if block can render in specified render pass
     * @param renderPass
     * @return
     */
    @LuaFunction
    public boolean CanRenderInPass(int renderPass)
    {
        return block.canRenderInPass(renderPass);
    }

    /**
     * Return true if block can sustain leaves
     * @param world
     * @param x
     * @param y
     * @param z
     * @return
     */
    @LuaFunction
    public boolean CanSustainLeaves(LuaIBlockAccess world, int x, int y, int z)
    {
        return block.canSustainLeaves(world.getBlockAccess(), x, y, z);
    }

    /**
     * Get block
     * @return
     */
    public Block getBlock()
    {
        return block;
    }

    @Override
    public boolean isContainer()
    {
        return true;
    }

    @Override
    public String GetType()
    {
        return "Block";
    }

    @Override
    public Object getObject()
    {
        return block;
    }
}
