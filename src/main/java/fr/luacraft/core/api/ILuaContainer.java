package fr.luacraft.core.api;

import com.naef.jnlua.util.LuaFunction;
import fr.luacraft.core.api.reflection.LuaJavaObject;

/**
 * A special {@link ILuaObject} that can contain a {@link Object}
 *
 * If a ILuaContainer that contains null is passed into Lua,
 * JNLua will automatically push nil instead
 *
 * To check if a {@link ILuaObject} is a {@link ILuaContainer}, use luacraft.IsContainer(obj)
 *
 * @author Zino
 */
public interface ILuaContainer extends ILuaObject
{
    /**
     * Get the contained java object, if any
     * @return
     */
    @LuaFunction
    LuaJavaObject GetContainedObject();
}
