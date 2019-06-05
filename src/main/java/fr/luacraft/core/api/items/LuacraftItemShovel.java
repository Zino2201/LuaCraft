package fr.luacraft.core.api.items;

import fr.luacraft.core.Luacraft;
import net.minecraft.item.ItemSpade;

/**
 * Luacraft shovel
 * @author Zino
 */
public class LuacraftItemShovel extends ItemSpade
{
    public LuacraftItemShovel(String id, String material)
    {
        super(ToolMaterial.valueOf(material));

        this.setUnlocalizedName(id);
        this.setTextureName(Luacraft.getInstance().getProxy().getCurrentMod().getModId() + ":" + id);
        this.setFull3D();
    }
}
