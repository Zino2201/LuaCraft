package fr.luacraft.core.api.gui;

import com.naef.jnlua.util.LuaFunction;
import fr.luacraft.core.api.ILuaObject;
import fr.luacraft.core.api.reflection.LuaJavaObject;
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
    @LuaFunction
    public String GetType()
    {
        return "GuiButton";
    }

    @Override
    @LuaFunction
    public boolean IsContainer()
    {
        return true;
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
