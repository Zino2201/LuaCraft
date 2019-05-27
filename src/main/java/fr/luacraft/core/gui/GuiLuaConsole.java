package fr.luacraft.core.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.resources.I18n;

public class GuiLuaConsole extends GuiScreen
{
    private GuiButton buttonSend;
    private GuiTextField textFieldCommand;

    @Override
    public void initGui()
    {
        super.initGui();

        FontRenderer fontRenderer = Minecraft.getMinecraft().fontRenderer;

        buttonSend = new GuiButton(
                0,
                this.width / 2 - 100,
                this.height - (this.height / 4) + 10,
                I18n.format("gui.luacraft.console.send", new Object[0]));

        int tfcW = height - 23;
        int tfcX = height - 23;

        textFieldCommand = new GuiTextField(
                fontRenderer,
                0,
                tfcW,
                tfcX,
                20
        );
        textFieldCommand.setText("...");
        textFieldCommand.setFocused(true);

        buttonList.add(buttonSend);
    }

    @Override
    protected void keyTyped(char p_73869_1_, int p_73869_2_)
    {
        super.keyTyped(p_73869_1_, p_73869_2_);

        textFieldCommand.textboxKeyTyped(p_73869_1_, p_73869_2_);
    }

    @Override
    public void updateScreen()
    {
        super.updateScreen();

        textFieldCommand.updateCursorCounter();
    }

    @Override
    public void drawScreen(int mX, int mY, float partialTicks)
    {
        this.drawDefaultBackground();

        super.drawScreen(mX, mY, partialTicks);

        textFieldCommand.drawTextBox();
    }

    protected void mouseClicked(int x, int y, int btn)
    {
        super.mouseClicked(x, y, btn);

        textFieldCommand.mouseClicked(x, y, btn);
    }

    @Override
    public boolean doesGuiPauseGame()
    {
        return false;
    }
}
