package fr.luacraft.core.api.meta;

import com.naef.jnlua.LuaState;
import fr.luacraft.core.api.ILuaContainer;

/**
 * A special {@link ILuaContainer} that is associated with a custom metatable instead
 * of using default jnlua.Object one
 *
 * To push a {@link ILuaMetaContainer} use {@code LuaMetaContainer#push}
 *
 * @author Zino
 */
@Deprecated
public interface ILuaMetaContainer extends ILuaContainer
{
    /**
     * Push contained object with container metatable
     * @param l
     */
    default void push(LuaState l)
    {
        LuaMetaUtil.pushJavaObject(GetContainedObject().GetJavaObject(), GetTypeName());
    }
}
