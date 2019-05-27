package fr.luacraft.api.libs;

import com.naef.jnlua.JavaFunction;
import com.naef.jnlua.LuaState;
import fr.luacraft.modloader.LuaGameRegistry;

/**
 * Base minecraft library
 * mc.*
 */
public class MinecraftLib
{
    public static JavaFunction AddRecipe = new JavaFunction()
    {
        @Override
        public int invoke(LuaState l)
        {
            String output = l.checkString(1);
            boolean shapeless = l.toBoolean(2);
            if(!l.isTable(3))
                throw new IllegalArgumentException("mc.AddRecipe arg 3 not a table");

            String[] slots = new String[9];
            l.pushValue(3);
            l.pushNil();
            int i = 0;
            while(l.next(-2))
            {
                //l.pushValue(-2);
                int index = l.checkInteger(-2);
                slots[index - 1] = l.checkString(-1);
                l.pop(1);
            }
            l.pop(1);
            LuaGameRegistry.addRecipe(output, shapeless, slots);
            return 0;
        }
    };

    /**
     * Initialize the library
     * @param l
     */
    public static void Initialize(LuaState l)
    {
        /** Global scope */

        /** Minecraft table */
        l.newTable();
        l.pushJavaObject(AddRecipe);
        l.setField(-2, "addRecipe");
        l.setGlobal("mc");
    }
}
