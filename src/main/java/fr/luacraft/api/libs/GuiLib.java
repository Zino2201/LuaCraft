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
    public static JavaFunction CreateScreen = new JavaFunction()
    {
        @Override
        public int invoke(LuaState l)
        {
            String name = l.checkString(1);
            LuaGuiScreen guiScreen = new LuaGuiScreen(new LuacraftGuiScreen(name));
            Luacraft.getInstance().getProxy().getCurrentMod().getRegistryData().addGui((LuacraftGuiScreen) guiScreen.getGuiScreen());
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
     * Get a gui screen
     */
    public static JavaFunction GetGuiFromName = new JavaFunction()
    {
        @Override
        public int invoke(LuaState l)
        {
            String name = l.checkString(1);
            if(name.contains(":"))
            {
                String[] formattedName = name.split(":");
                LuaGuiScreen guiScreen = new LuaGuiScreen(Luacraft.getInstance().getModLoader().getModByID(formattedName[0])
                        .getRegistryData().getGuiScreenFromName(formattedName[1]));
                l.pushJavaObject(guiScreen);
                return 1;
            }

            l.pushNil();
            return 1;
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
        l.pushJavaObject(CreateScreen);
        l.setField(-2, "createScreen");
        l.pushJavaObject(OpenGui);
        l.setField(-2, "openGui");
        l.pushJavaObject(GetGuiFromName);
        l.setField(-2, "getGuiFromName");
        l.setGlobal("gui");
    }
}
