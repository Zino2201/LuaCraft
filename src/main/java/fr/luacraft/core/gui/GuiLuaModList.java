package fr.luacraft.core.gui;

import cpw.mods.fml.client.GuiScrollingList;
import fr.luacraft.core.Luacraft;
import fr.luacraft.modloader.LuacraftMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.Tessellator;

/**
 * Gui lua mod list
 * Based from cpw's GuiSlotModList
 */
public class GuiLuaModList extends GuiScrollingList
{
    private GuiLuaModMenu modMenu;

    public GuiLuaModList(GuiLuaModMenu menu, int listWidth)
    {
        super(Minecraft.getMinecraft(), listWidth, menu.height, 32,
                menu.height - 66 + 4, 10, 35);
        this.modMenu = menu;
    }

    @Override
    protected int getSize()
    {
        return Luacraft.getInstance().getModLoader().getMods().size();
    }

    @Override
    protected void elementClicked(int index, boolean doubleClick)
    {
        modMenu.selectMod(index);
    }

    @Override
    protected boolean isSelected(int index)
    {
        return modMenu.isSelected(index);
    }

    @Override
    protected void drawBackground()
    {
        modMenu.drawDefaultBackground();
    }

    @Override
    protected int getContentHeight()
    {
        return (this.getSize()) * 35 + 1;
    }

    @Override
    protected void drawSlot(int var1, int var2, int var3, int var4, Tessellator var5)
    {
        LuacraftMod mod = Luacraft.getInstance().getModLoader().getMods().get(var1);

        FontRenderer fontRenderer = Minecraft.getMinecraft().fontRenderer;

        fontRenderer.drawString(fontRenderer.trimStringToWidth(mod.getName(),
                listWidth - 10), this.left + 3 , var3 + 2, 0xFFFFFF);
        fontRenderer.drawString(fontRenderer.trimStringToWidth("v" + mod.getDisplayVersion(),
                listWidth - 10), this.left + 3 , var3 + 12, 0xCCCCCC);
        fontRenderer.drawString(fontRenderer.trimStringToWidth("Luacraft v" + mod.getLuacraftVersion(),
                listWidth - 10), this.left + 3 , var3 + 22,
                mod.isObsolete() ? 0xFF0000 : 0xCCCCCC);
    }
}
