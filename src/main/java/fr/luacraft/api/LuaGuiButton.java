package fr.luacraft.api;

import fr.luacraft.modloader.ILuaObject;
import net.minecraft.client.gui.GuiButton;

/**
 * Represents a GuiButton in Lua
 */
public class LuaGuiButton implements ILuaObject
{
    private GuiButton guiButton;

    public LuaGuiButton(GuiButton guiButton)
    {
        this.guiButton = guiButton;
    }

    @Override
    public String GetType()
    {
        return "GuiButton";
    }

    @Override
    public Object getObject()
    {
        return guiButton;
    }

    public GuiButton getGuiButton()
    {
        return guiButton;
    }
}
