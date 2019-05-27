package fr.luacraft.api.classes;

import fr.luacraft.core.Luacraft;
import fr.luacraft.modloader.ContainerObjectType;
import fr.luacraft.modloader.ILuaContainerObject;
import net.minecraft.item.Item;

public class LuacraftItem extends Item implements ILuaContainerObject
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
