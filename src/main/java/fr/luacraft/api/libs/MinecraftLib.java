package fr.luacraft.api.libs;

import com.naef.jnlua.JavaFunction;
import com.naef.jnlua.LuaState;
import fr.luacraft.api.LuaObject;
import fr.luacraft.core.Luacraft;
import fr.luacraft.core.LuaObjectManager;

/**
 * Base minecraft library
 * mc.*
 */
public class MinecraftLib
{
    /**
     * Create a new lua object
     */
    public static JavaFunction NewLuaObject = new JavaFunction()
    {
        @Override
        public int invoke(LuaState l)
        {
            String name = l.checkString(1);
            String id = l.checkString(2);
            Luacraft.getLogger().info("Creating " + id + " from class " + name);
            LuaObject object = LuaObjectManager.createFromClass(name, id);
            l.pushJavaObject(object);
            return 1;
        }
    };

    /**
     * Initialize the library
     * @param l
     */
    public static void Initialize(LuaState l)
    {
        /** Minecraft table */
        l.newTable();

        l.pushJavaFunction(NewLuaObject);
        l.setField(-2, "new");

        l.setGlobal("mc");
    }
}
