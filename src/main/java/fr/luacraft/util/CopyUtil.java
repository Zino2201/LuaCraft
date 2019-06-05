package fr.luacraft.util;

import com.google.gson.Gson;

/**
 * Utils functions for copying
 * @author Zino
 */
public class CopyUtil
{
    /**
     * Perform a deep copy using Gson
     * @param in
     * @param <T>
     * @return
     */
    public static <T> T deepCopy(T in)
    {
        Gson gson = new Gson();
        return (T) gson.fromJson(gson.toJson(in), in.getClass());
    }
}
