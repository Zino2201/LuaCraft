package fr.luacraft.core.api.command;

import com.naef.jnlua.util.LuaFunction;
import fr.luacraft.core.api.ILuaObject;
import fr.luacraft.core.api.reflection.LuaJavaObject;
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
    @LuaFunction
    public String GetType()
    {
        return "CommandSender";
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
        return new LuaJavaObject(commandSender);
    }
}
