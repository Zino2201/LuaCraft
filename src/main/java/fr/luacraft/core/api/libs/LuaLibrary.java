package fr.luacraft.core.api.libs;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.luacraft.core.proxy.ProxyType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Lua libraries are collections of functions exposed to Lua in a table
 * Example: luacraft.GetVersion()
 *
 * When a class is marked as LuaLibrary, when registered using SharedProxy#registerLuaLibrary
 * Luacraft will try to call the method "initialize" if it exists taking in parameter the proxy's LuaState
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface LuaLibrary
{
    ProxyType side() default ProxyType.SHARED;
}
