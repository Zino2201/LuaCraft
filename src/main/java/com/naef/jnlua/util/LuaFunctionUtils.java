package com.naef.jnlua.util;

import java.lang.reflect.Method;

public class LuaFunctionUtils
{
    public static boolean validLuaFunction(Method method)
    {
        return method.isAnnotationPresent(LuaFunction.class);
    }
}
