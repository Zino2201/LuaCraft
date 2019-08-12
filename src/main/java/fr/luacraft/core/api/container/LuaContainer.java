package fr.luacraft.core.api.container;

import fr.luacraft.core.api.ILuaContainer;
import fr.luacraft.core.api.reflection.LuaJavaObject;
import net.minecraft.inventory.Container;

public class LuaContainer implements ILuaContainer
{
    private Container container;

    public LuaContainer(Container container)
    {
        this.container = container;
    }

    @Override
    public String GetTypeName()
    {
        return "Container";
    }

    @Override
    public LuaJavaObject GetContainedObject()
    {
        return new LuaJavaObject(container);
    }
}
