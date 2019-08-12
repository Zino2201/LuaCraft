package fr.luacraft.core.api.gui;

import com.naef.jnlua.util.LuaFunction;
import fr.luacraft.core.api.ILuaContainer;
import fr.luacraft.core.api.reflection.LuaJavaObject;
import net.minecraft.client.gui.GuiButton;

/**
 * Represents a GuiButton in Lua
 */
public class LuaGuiButton implements ILuaContainer
{
    private GuiButton guiButton;

    public LuaGuiButton(GuiButton guiButton)
    {
        this.guiButton = guiButton;
    }

    @Override
    @LuaFunction
    public String GetTypeName()
    {
        return "GuiButton";
    }

    @Override
    @LuaFunction
    public LuaJavaObject GetContainedObject()
    {
        return new LuaJavaObject(guiButton);
    }

    public GuiButton getGuiButton()
    {
        return guiButton;
    }
}
