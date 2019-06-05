package fr.luacraft.core.api.items;

import fr.luacraft.core.Luacraft;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

/**
 * Luacraft item armor
 * @author Zino
 */
public class LuacraftItemArmor extends ItemArmor
{
    public LuacraftItemArmor(String id, String material, int armorPart)
    {
        super(ArmorMaterial.valueOf(material), 0, armorPart);

        this.setUnlocalizedName(id);
        this.setTextureName(Luacraft.getInstance().getProxy().getCurrentMod().getModId() + ":" + id);
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
    {
        if(slot == 0 || slot == 1 || slot == 3)
            return Luacraft.getInstance().getProxy().getCurrentMod().getModId() + ":textures/armor/" + getArmorMaterial().name() + "_layer_1.png";
        else
            return Luacraft.getInstance().getProxy().getCurrentMod().getModId() + ":textures/armor/" + getArmorMaterial().name() + "_layer_2.png";
    }
}
