package fr.luacraft.core.api.items;

import fr.luacraft.core.Luacraft;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBucket;

/**
 * A item bucket compatible with Luacraft hooks
 * @author Zino
 */
public class LuacraftItemBucket extends ItemBucket
{
    public LuacraftItemBucket(String id, Block block)
    {
        super(block);

        this.setUnlocalizedName(id);
        this.setTextureName(Luacraft.getInstance().getProxy().getCurrentMod().getModId() + ":" + id);
        this.setFull3D();
    }
}
