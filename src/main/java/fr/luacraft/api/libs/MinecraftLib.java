package fr.luacraft.api.libs;

import com.naef.jnlua.LuaState;

/**
 * Base minecraft library
 * mc.*
 */
public class MinecraftLib
{
    /**
     * Initialize the library
     * @param l
     */
    public static void Initialize(LuaState l)
    {
        /** Minecraft table */
        l.newTable();

        l.setGlobal("mc");
    }
}
