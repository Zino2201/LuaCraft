package fr.luacraft.core.api.command;

import com.naef.jnlua.util.LuaFunction;
import fr.luacraft.core.api.ILuaObject;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

/**
 * Lua command sender object
 * @author Zino
 */
public class LuaCommandSender implements ILuaObject
{
    private ICommandSender commandSender;

    public LuaCommandSender(ICommandSender commandSender)
    {
        this.commandSender = commandSender;
    }

    @LuaFunction
    public void SendMessage(String message)
    {
        commandSender.addChatMessage(new ChatComponentText(message));
    }

    @Override
    public String GetType()
    {
        return "CommandSender";
    }

    @Override
    public boolean IsContainer()
    {
        return true;
    }

    @Override
    public Object GetContainedObject()
    {
        return commandSender;
    }
}
