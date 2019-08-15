package fr.luacraft.core.api.meta;

import java.lang.annotation.*;

/**
 * When a class is marked as {@link LuaMetaClass},
 *  Luacraft will except and call a initialize(LuaState) function
 */
@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface LuaMetaClass
{
    
}
