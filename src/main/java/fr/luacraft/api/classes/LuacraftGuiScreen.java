package fr.luacraft.api.classes;

import fr.luacraft.core.LuaHookManager;
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

    public LuacraftGuiScreen()
    {

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

        LuaHookManager.call(this, "OnInit");
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode)
    {
        super.keyTyped(typedChar, keyCode);

        LuaHookManager.call(this, "OnKeyTyped", typedChar, keyCode);
    }

    @Override
    public void updateScreen()
    {
        super.updateScreen();

        LuaHookManager.call(this, "OnUpdateScreen");
    }

    @Override
    public void drawScreen(int mX, int mY, float partialTicks)
    {
        this.drawDefaultBackground();

        super.drawScreen(mX, mY, partialTicks);

        LuaHookManager.call(this, "OnDrawScreen", mX, mY, partialTicks);
    }

    @Override
    protected void mouseClicked(int x, int y, int button)
    {
        super.mouseClicked(x, y, button);

        LuaHookManager.call(this, "OnMouseClicked", x, y, button);
    }

    @Override
    public boolean doesGuiPauseGame()
    {
        return guiPauseGame;
    }
}
