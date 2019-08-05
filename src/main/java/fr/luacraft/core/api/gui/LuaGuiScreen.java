package fr.luacraft.core.api.gui;

import com.naef.jnlua.util.LuaFunction;
import fr.luacraft.core.api.ILuaObject;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;

/**
 * Represents a GuiScreen in Lua
 * @author Zino
 */
public class LuaGuiScreen implements ILuaObject
{
    private GuiScreen guiScreen;

    public LuaGuiScreen(GuiScreen guiScreen)
    {
        this.guiScreen = guiScreen;
    }

    /**
     * Set gui pause game variable
     * Works only for class LuacraftGuiScreen
     * @param guiPauseGame
     */
    @LuaFunction
    public void SetGuiPauseGame(boolean guiPauseGame)
    {
        if(guiScreen instanceof LuacraftGuiScreen)
            ((LuacraftGuiScreen) guiScreen).setGuiPauseGame(guiPauseGame);
    }

    /**
     * Add a button to the gui screen
     * Works only for class LuacraftGuiScreen
     * @param x
     * @param y
     * @param width
     * @param height
     * @param text
     * @return
     */
    @LuaFunction
    public LuaGuiButton AddButton(int x, int y, int width, int height, String text)
    {
        if(text.contains(":"))
        {
            String[] splittedText = text.split(":");
            text = I18n.format(splittedText[1], new Object[0]);
        }


        LuaGuiButton guiButton = new LuaGuiButton(new LuacraftGuiButton(0, x, y, width, height, text));
        if(guiScreen instanceof LuacraftGuiScreen)
            ((LuacraftGuiScreen) guiScreen).addButton(guiButton.getGuiButton());
        return guiButton;
    }

    /**
     * Get gui width
     * @return
     */
    @LuaFunction
    public int GetWidth()
    {
        return guiScreen.width;
    }

    /**
     * Get gui height
     * @return
     */
    @LuaFunction
    public int GetHeight()
    {
        return guiScreen.height;
    }

    @Override
    public String GetType()
    {
        return "GuiScreen";
    }

    @Override
    public boolean isContainer()
    {
        return true;
    }

    @Override
    public Object getObject()
    {
        return guiScreen;
    }

    public GuiScreen getGuiScreen()
    {
        return guiScreen;
    }
}
