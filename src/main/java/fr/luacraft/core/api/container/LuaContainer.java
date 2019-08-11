package fr.luacraft.core.api.container;

import fr.luacraft.core.api.ILuaObject;
import fr.luacraft.core.api.reflection.LuaJavaObject;
import net.minecraft.inventory.Container;

public class LuaContainer implements ILuaObject
{
    private Container container;

    public LuaContainer(Container container)
    {
        this.container = container;
    }

    @Override
    public String GetType()
    {
        return "Container";
    }

    @Override
    public boolean IsContainer()
    {
        return true;
    }

    @Override
    public LuaJavaObject GetContainedObject()
    {
        return new LuaJavaObject(container);
    }
}
