package fr.luacraft.core.api.gui;

import fr.luacraft.core.api.ILuaObject;
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
    public boolean isContainer()
    {
        return true;
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
