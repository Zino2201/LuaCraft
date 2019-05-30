package fr.luacraft.api.classes;

import fr.luacraft.api.IItemContainerObject;
import fr.luacraft.core.Luacraft;
import fr.luacraft.modloader.ContainerObjectType;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;

public class LuacraftItemBucket extends ItemBucket implements IItemContainerObject
{
    public LuacraftItemBucket(String id, Block block)
    {
        super(block);

        this.setUnlocalizedName(id);
        this.setTextureName(Luacraft.getInstance().getProxy().getCurrentMod().getModId() + ":" + id);
        this.setFull3D();
    }

    @Override
    public ContainerObjectType getType()
    {
        return ContainerObjectType.ITEM;
    }

    @Override
    public Item getItem()
    {
        return this;
    }
}
