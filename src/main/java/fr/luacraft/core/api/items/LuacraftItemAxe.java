package fr.luacraft.core.api.items;

import fr.luacraft.core.Luacraft;
import net.minecraft.item.ItemAxe;

/**
 * Luacraft axe
 * @author Zino
 */
public class LuacraftItemAxe extends ItemAxe
{
    public LuacraftItemAxe(String id, String material)
    {
        super(ToolMaterial.valueOf(material));

        this.setUnlocalizedName(id);
        this.setTextureName(Luacraft.getInstance().getProxy().getCurrentMod().getModId() + ":" + id);
        this.setFull3D();
    }
}