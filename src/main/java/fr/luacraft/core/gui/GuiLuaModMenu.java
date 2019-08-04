package fr.luacraft.core.gui;

import net.minecraft.client.gui.GuiScreen;

public class GuiLuaModMenu extends GuiScreen
{
    private GuiScreen mainMenu;

    public GuiLuaModMenu(GuiScreen mainMenu)
    {
        this.mainMenu = mainMenu;
    }

    @Override
    public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_)
    {
        this.drawDefaultBackground();

        this.drawCenteredString(this.fontRendererObj, "Lua mod list", this.width / 2, 16, 0xFFFFFF);

        super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
    }
}
