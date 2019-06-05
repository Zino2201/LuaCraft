package fr.luacraft.core.api.libs;

import com.naef.jnlua.JavaFunction;
import com.naef.jnlua.LuaState;
import net.minecraft.client.resources.I18n;

/**
 * i18n library
 * @author Zino
 */
public class I18NLib
{
    /**
     * Format a string
     */
    public static JavaFunction Format = new JavaFunction()
    {
        @Override
        public int invoke(LuaState l)
        {
            String format = l.checkString(1);
            String result = I18n.format(format);
            l.pushString(result);
            return 1;
        }
    };

    public static void initialize(LuaState l)
    {
        /** i18n table */
        l.newTable();

        l.pushJavaFunction(Format);
        l.setField(-2, "Format");

        l.setGlobal("i18n");
    }
}
