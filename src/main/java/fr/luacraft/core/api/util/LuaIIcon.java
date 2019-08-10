package fr.luacraft.core.api.util;

import fr.luacraft.core.api.ILuaObject;
import net.minecraft.util.IIcon;

public class LuaIIcon implements ILuaObject
{
    private IIcon icon;

    public LuaIIcon(IIcon icon)
    {
        this.icon = icon;
    }

    @Override
    public String GetType()
    {
        return "Icon";
    }

    @Override
    public boolean IsContainer()
    {
        return true;
    }

    @Override
    public Object GetContainedObject()
    {
        return icon;
    }
}
