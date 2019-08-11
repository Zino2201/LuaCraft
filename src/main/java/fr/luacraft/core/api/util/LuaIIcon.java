package fr.luacraft.core.api.util;

import com.naef.jnlua.util.LuaFunction;
import fr.luacraft.core.api.ILuaObject;
import fr.luacraft.core.api.reflection.LuaJavaObject;
import net.minecraft.util.IIcon;

public class LuaIIcon implements ILuaObject
{
    private IIcon icon;

    public LuaIIcon(IIcon icon)
    {
        this.icon = icon;
    }

    @Override
    @LuaFunction
    public String GetType()
    {
        return "Icon";
    }

    @Override
    @LuaFunction
    public boolean IsContainer()
    {
        return true;
    }

    @Override
    @LuaFunction
    public LuaJavaObject GetContainedObject()
    {
        return new LuaJavaObject(icon);
    }
}
