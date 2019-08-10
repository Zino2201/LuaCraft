package fr.luacraft.core.gui;

import com.google.common.base.Joiner;
import cpw.mods.fml.common.ModContainer;
import fr.luacraft.core.Luacraft;
import fr.luacraft.modloader.LuacraftMod;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;

/**
 * Lua mod menu
 * Based off cpw's GuiModList
 */
public class GuiLuaModMenu extends GuiScreen
{
    private GuiScreen mainMenu;
    private GuiLuaModList modList;
    private int currentModIndex = -1;
    private LuacraftMod currentMod;
    private int listWidth;

    public GuiLuaModMenu(GuiScreen mainMenu)
    {
        this.mainMenu = mainMenu;
    }

    @Override
    public void initGui()
    {
        listWidth = 125;

        modList = new GuiLuaModList(this, listWidth);
        modList.registerScrollButtons(this.buttonList, 7, 8);

        this.buttonList.add(new GuiButton(2201+1, this.width / 2 - 75, this.height - 38, I18n.format("gui.done")));
    }

    @Override
    protected void actionPerformed(GuiButton button)
    {
        if (button.enabled)
        {
            switch (button.id)
            {
                case 2201+1:
                    this.mc.displayGuiScreen(this.mainMenu);
                    return;
            }
        }

        super.actionPerformed(button);
    }

    @Override
    public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_)
    {
        this.drawDefaultBackground();

        this.drawCenteredString(this.fontRendererObj, "Lua mod list", this.width / 2, 16, 0xFFFFFF);

        modList.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);

        if(currentMod != null)
        {
            int shifty = 35;
            int offset = listWidth + 20;

            fontRendererObj.drawStringWithShadow(String.format("%s v%s (%s)",
                    currentMod.getName(), currentMod.getVersion(), currentMod.getModId()), offset, shifty, 0xFFFFFF);
            shifty += 12;
            shifty = drawLine("Made for Luacraft v" + currentMod.getLuacraftVersion(), offset, shifty);
            if(currentMod.isObsolete())
            {
                fontRendererObj.drawSplitString(String.format("Warning: Your version of Luacraft is not " +
                                "supported by this mod. (supported: %s, current: %s)",
                                currentMod.getLuacraftVersion(), Luacraft.VERSION),
                        offset, shifty, width - offset - 20, 0xFF0000);
                shifty += 25;
            }
            shifty = drawLine(String.format("Author(s): %s", Joiner.on(", ").join(currentMod.getModInfo().getAuthors())), offset, shifty);
            shifty += 10;
            shifty = drawLine(String.format("See also %s", currentMod.getModInfo().getWebsite()), offset, shifty);

            int rightSide = this.width - offset - 20;
            if (rightSide > 20)
            {
                shifty += 10;
                fontRendererObj.drawSplitString(currentMod.getModInfo().getDescription(), offset,
                        shifty, rightSide, 0xDDDDDD);
            }

        }

        super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
    }

    public void selectMod(int mod)
    {
        currentModIndex = mod;
        currentMod = Luacraft.getInstance().getModLoader().getMods().get(mod);
    }

    public boolean isSelected(int mod)
    {
        return currentModIndex == mod;
    }

    public int drawLine(String line, int offset, int shifty, int color)
    {
        this.fontRendererObj.drawString(line, offset, shifty, color);
        return shifty + 10;
    }

    public int drawLine(String line, int offset, int shifty)
    {
        return drawLine(line, offset, shifty, 0xd7edea);
    }
}


