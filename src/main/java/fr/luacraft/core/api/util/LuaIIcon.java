package fr.luacraft.core.api.util;

import com.naef.jnlua.util.LuaFunction;
import fr.luacraft.core.api.ILuaContainer;
import fr.luacraft.core.api.reflection.LuaJavaObject;
import net.minecraft.util.IIcon;

public class LuaIIcon implements ILuaContainer
{
    private IIcon icon;

    public LuaIIcon(IIcon icon)
    {
        this.icon = icon;
    }

    @Override
    @LuaFunction
    public String GetTypeName()
    {
        return "Icon";
    }

    @Override
    @LuaFunction
    public LuaJavaObject GetContainedObject()
    {
        return new LuaJavaObject(icon);
    }
}
