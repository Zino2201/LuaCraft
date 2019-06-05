package fr.luacraft.core.api.items;

import fr.luacraft.core.Luacraft;
import net.minecraft.item.ItemPickaxe;

/**
 * Luacraft pickaxe
 * @author Zino
 */
public class LuacraftItemPickaxe extends ItemPickaxe
{
    public LuacraftItemPickaxe(String id, String material)
    {
        super(ToolMaterial.valueOf(material));

        this.setUnlocalizedName(id);
        this.setTextureName(Luacraft.getInstance().getProxy().getCurrentMod().getModId() + ":" + id);
        this.setFull3D();
    }
}
