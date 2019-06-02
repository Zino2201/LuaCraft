package fr.luacraft.api.libs;

import com.naef.jnlua.JavaFunction;
import com.naef.jnlua.LuaState;
import fr.luacraft.api.LuaGuiScreen;
import fr.luacraft.api.classes.LuacraftGuiScreen;
import fr.luacraft.core.Luacraft;

/**
 * Gui library, used for manipulating GUIs in Lua
 * @author Zino
 */
public class GuiLib
{
    /**
     * Create a LuacraftGuiScreen object
     */
    public static JavaFunction GuiScreen = new JavaFunction()
    {
        @Override
        public int invoke(LuaState l)
        {
            LuaGuiScreen guiScreen = new LuaGuiScreen(new LuacraftGuiScreen());
            l.pushJavaObject(guiScreen);
            return 1;
        }
    };

    /**
     * Open a GuiScreen
     * Client-side only
     */
    public static JavaFunction OpenGui = new JavaFunction()
    {
        @Override
        public int invoke(LuaState l)
        {
            LuaGuiScreen guiScreen = l.checkJavaObject(1, LuaGuiScreen.class);
            Luacraft.getInstance().getProxy().openGUI(guiScreen.getGuiScreen());
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
        l.pushJavaObject(GuiScreen);
        l.setGlobal("GuiScreen");

        /** Minecraft table */
        l.newTable();
        l.pushJavaObject(OpenGui);
        l.setField(-2, "openGui");
        l.setGlobal("gui");
    }
}
