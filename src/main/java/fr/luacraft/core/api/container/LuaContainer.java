package fr.luacraft.core.api.container;

import fr.luacraft.core.api.ILuaObject;
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
    public boolean isContainer()
    {
        return true;
    }

    @Override
    public Object getObject()
    {
        return container;
    }
}
