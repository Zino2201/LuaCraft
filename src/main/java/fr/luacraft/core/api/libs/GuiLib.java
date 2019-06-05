package fr.luacraft.core.api.libs;

import com.naef.jnlua.JavaFunction;
import com.naef.jnlua.LuaState;
import fr.luacraft.core.Luacraft;
import fr.luacraft.core.api.gui.LuaGuiScreen;
import fr.luacraft.core.api.gui.LuacraftGuiScreen;

/**
 * Gui library, used for manipulating GUIs in Lua
 * @author Zino
 */
public class GuiLib
{
    /**
     * Create a LuacraftGuiScreen object
     */
    public static JavaFunction CreateScreen = new JavaFunction()
    {
        @Override
        public int invoke(LuaState l)
        {
            String name = l.checkString(1);
            LuaGuiScreen guiScreen = new LuaGuiScreen(new LuacraftGuiScreen(name));
            Luacraft.getInstance().getProxy().getCurrentMod().getRegistryData().addGui(guiScreen.getGuiScreen());
            l.pushJavaObject(guiScreen);
            return 1;
        }
    };

    /**
     * Open a GuiScreen
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
     * initialize the library
     * @param l
     */
    public static void initialize(LuaState l)
    {
        /** Global scope */

        /** Minecraft table */
        l.newTable();
        l.pushJavaObject(CreateScreen);
        l.setField(-2, "CreateScreen");
        l.pushJavaObject(OpenGui);
        l.setField(-2, "OpenGui");
        l.setGlobal("gui");
    }
}
