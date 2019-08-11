package fr.luacraft.core.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 * The lua mod button seen in main menu
 * @author Zino
 */
public class GuiLuaModMenuButton extends GuiButton
{
    public static final ResourceLocation BUTTON_ICON = new ResourceLocation("luacraft",
            "textures/luacraft_buttons.png");

    public GuiLuaModMenuButton(int id, int x, int y)
    {
        super(id, x, y, 20, 20, "");
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY)
    {
        mc.getTextureManager().bindTexture(BUTTON_ICON);
        GL11.glColor4f(1, 1, 1, 1);

        Gui.func_146110_a(this.xPosition, this.yPosition, 0, 0, this.width, this.height, 20, 20);
    }
}
