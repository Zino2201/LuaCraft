package fr.luacraft.core.gui;

import cpw.mods.fml.client.config.GuiButtonExt;
import fr.luacraft.core.Luacraft;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 * The lua mod button seen in main menu
 * @author Zino
 */
public class GuiLuaModMenuButton extends GuiButtonExt
{
    public static final ResourceLocation BUTTON_ICON = new ResourceLocation("luacraft",
            "textures/luacraft_buttons.png");

    private GuiMainMenu menu;

    public GuiLuaModMenuButton(GuiMainMenu menu, int id, int x, int y)
    {
        super(id, x, y, 20, 20, "");

        this.menu = menu;

        if(Luacraft.getInstance().getModLoader().getMods().size() > 0)
            enabled = true;
        else
            enabled = false;
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY)
    {
        mc.getTextureManager().bindTexture(BUTTON_ICON);
        GL11.glColor4f(1, 1, 1, 1);

        int x = 0;
        int y = 0;

        if(enabled)
        {
            boolean hovered = mouseX >= this.xPosition
                    && mouseY >= this.yPosition
                    && mouseX < this.xPosition + this.width
                    && mouseY < this.yPosition + this.height;
            if(!hovered)
                y = 80;
            else
                y = 40;
        }
        else
        {
            y = 0;
        }

        Gui.func_146110_a(this.xPosition, this.yPosition, x, y,
                this.width, this.height, 20, 60);
    }
}
