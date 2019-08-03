package fr.luacraft.core.api.items;

import fr.luacraft.core.api.ILuaObject;
import net.minecraft.item.ItemStack;

public class LuaItemStack implements ILuaObject
{
    private ItemStack itemStack;

    public LuaItemStack(ItemStack itemStack)
    {
        this.itemStack = itemStack;
    }

    public ItemStack getItemStack()
    {
        return itemStack;
    }

    @Override
    public String GetType()
    {
        return "ItemStack";
    }

    @Override
    public boolean isContainer() {
        return true;
    }

    @Override
    public Object getObject() {
        return itemStack;
    }
}
