package fr.luacraft.api.classes;

import net.minecraft.client.gui.GuiButton;

/**
 * A gui button compatible with Luacraft hooks
 * @author Zino
 */
public class LuacraftGuiButton extends GuiButton
{
    public LuacraftGuiButton(int id, int x, int y, int w, int h, String str)
    {
        super(id, x, y, w, h, str);
    }
}
