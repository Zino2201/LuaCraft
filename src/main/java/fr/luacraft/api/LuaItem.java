package fr.luacraft.api;

import net.minecraft.item.Item;

public class LuaItem extends LuaObject
{
    private Item item;

    public LuaItem(String id)
    {
        super(id);

        this.item = new Item().setUnlocalizedName(id);
    }

    public Item getItem()
    {
        return item;
    }

    @Override
    public String getTypeName()
    {
        return "Item";
    }
}
