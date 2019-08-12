package fr.luacraft.core.api.items;

import com.naef.jnlua.util.LuaFunction;
import fr.luacraft.core.api.ILuaContainer;
import fr.luacraft.core.api.reflection.LuaJavaObject;
import net.minecraft.item.ItemStack;

/**
 * A lua item stack
 * @author Zino
 */
public class LuaItemStack implements ILuaContainer
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
    @LuaFunction
    public String GetTypeName()
    {
        return "ItemStack";
    }

    @Override
    @LuaFunction
    public LuaJavaObject GetContainedObject()
    {
        return new LuaJavaObject(itemStack);
    }
}
