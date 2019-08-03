package fr.luacraft.core.api.items;

import fr.luacraft.core.Luacraft;
import net.minecraft.item.ItemFood;

/**
 * A lua food
 * @author Zino
 */
public class LuacraftItemFood extends ItemFood
{
    public LuacraftItemFood(String id, int healAmount, float saturation, boolean isWolfFavoriteFood)
    {
        super(healAmount, saturation, isWolfFavoriteFood);

        this.setUnlocalizedName(id);
        this.setTextureName(Luacraft.getInstance().getProxy().getCurrentMod().getModId() + ":" + id);
        this.setFull3D();
    }
}
