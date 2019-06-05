package fr.luacraft.core.api.items;

import fr.luacraft.core.Luacraft;
import net.minecraft.item.ItemHoe;

/**
 * Luacraft hoe
 * @author Zino
 */
public class LuacraftItemHoe extends ItemHoe
{
    public LuacraftItemHoe(String id, String material)
    {
        super(ToolMaterial.valueOf(material));

        this.setUnlocalizedName(id);
        this.setTextureName(Luacraft.getInstance().getProxy().getCurrentMod().getModId() + ":" + id);
        this.setFull3D();
    }
}
