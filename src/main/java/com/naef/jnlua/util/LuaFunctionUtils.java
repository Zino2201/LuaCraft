package com.naef.jnlua.util;

import java.lang.reflect.Method;

public class LuaFunctionUtils
{
    public static boolean validLuaFunction(Class clazz,
                                           Method method)
    {
        if(method.isAnnotationPresent(LuaFunction.class))
            return true;

        return false;
    }
}
