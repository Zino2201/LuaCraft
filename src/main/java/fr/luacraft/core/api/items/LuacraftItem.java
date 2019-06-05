package fr.luacraft.core.api.items;

import fr.luacraft.core.Luacraft;
import net.minecraft.item.Item;

/**
 * A item compatible with Luacraft hooks
 * @author Zino
 */
public class LuacraftItem extends Item
{
    public LuacraftItem(String id)
    {
        this.setUnlocalizedName(id);
        this.setTextureName(Luacraft.getInstance().getProxy().getCurrentMod().getModId() + ":" + id);
        this.setFull3D();
    }
}
