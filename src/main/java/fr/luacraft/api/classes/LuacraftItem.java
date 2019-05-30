package fr.luacraft.api.classes;

import fr.luacraft.api.IItemContainerObject;
import fr.luacraft.core.Luacraft;
import fr.luacraft.modloader.ContainerObjectType;
import net.minecraft.item.Item;

public class LuacraftItem extends Item implements IItemContainerObject
{
    public LuacraftItem(String id)
    {
        this.setUnlocalizedName(id);
        this.setTextureName(Luacraft.getInstance().getProxy().getCurrentMod().getModId() + ":" + id);
        this.setFull3D();
    }

    @Override
    public ContainerObjectType getType()
    {
        return ContainerObjectType.ITEM;
    }
}
