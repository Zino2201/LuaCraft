package fr.luacraft.core.api.items;

import fr.luacraft.core.Luacraft;
import net.minecraft.item.ItemSword;

/**
 * Luacraft sword
 * @author Zino
 */
public class LuacraftItemSword extends ItemSword
{
    public LuacraftItemSword(String id, String material)
    {
        super(ToolMaterial.valueOf(material));

        this.setUnlocalizedName(id);
        this.setTextureName(Luacraft.getInstance().getProxy().getCurrentMod().getModId() + ":" + id);
        this.setFull3D();
    }
}
