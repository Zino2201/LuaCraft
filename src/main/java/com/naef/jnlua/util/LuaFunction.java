package com.naef.jnlua.util;

import java.lang.annotation.*;

/**
 * Mark a function as accessible in Lua
 * This is a way to prevent internal functions from being accessed in Lua
 *
 * This annotation also got a extra meta String array property for supplying metadata
 * @author Zino
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface LuaFunction
{
    String[] meta() default "";
}
