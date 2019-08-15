package fr.luacraft.core.api.gui;

import fr.luacraft.core.api.hooks.LuaHookManagerOLD;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

/**
 * A gui screen compatible with Luacraft Hooks
 * @author Zino
 */
public class LuacraftGuiScreen extends GuiScreen
{
    /**
     * Gui should pause game ?
     */
    private boolean guiPauseGame;

    /**
     * Gui internal name
     */
    private String name;

    public LuacraftGuiScreen(String name)
    {
        this.name = name;
    }

    public void setGuiPauseGame(boolean guiPauseGame)
    {
        this.guiPauseGame = guiPauseGame;
    }
    public void addButton(GuiButton button)
    {
        this.buttonList.add(button);
    }

    @Override
    public void initGui()
    {
        super.initGui();

        LuaHookManagerOLD.call(this, "OnInit");
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode)
    {
        super.keyTyped(typedChar, keyCode);

        LuaHookManagerOLD.call(this, "OnKeyTyped", typedChar, keyCode);
    }

    @Override
    public void updateScreen()
    {
        super.updateScreen();

        LuaHookManagerOLD.call(this, "OnUpdateScreen");
    }

    @Override
    public void drawScreen(int mX, int mY, float partialTicks)
    {
        this.drawDefaultBackground();

        super.drawScreen(mX, mY, partialTicks);

        LuaHookManagerOLD.call(this, "OnDrawScreen", mX, mY, partialTicks);
    }

    @Override
    protected void mouseClicked(int x, int y, int button)
    {
        super.mouseClicked(x, y, button);

        LuaHookManagerOLD.call(this, "OnMouseClicked", x, y, button);
    }

    @Override
    public boolean doesGuiPauseGame()
    {
        return guiPauseGame;
    }

    public String getName()
    {
        return name;
    }
}
