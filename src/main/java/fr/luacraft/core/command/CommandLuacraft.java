package fr.luacraft.core.command;

import fr.luacraft.core.Luacraft;
import fr.luacraft.core.gui.GuiLuaConsole;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.util.ChatComponentText;

public class CommandLuacraft extends CommandBase
{
    @Override
    public String getCommandName()
    {
        return "luacraft";
    }

    @Override
    public String getCommandUsage(ICommandSender sender)
    {
        return "commands.luacraft.usage";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args)
    {
        if(args.length > 0)
        {
            if(args[0].matches("console"))
            {
                Luacraft.getInstance().getProxy().openGUI(new GuiLuaConsole());
            }
            else if(args[0].matches("help"))
            {
                sender.addChatMessage(new ChatComponentText("help"));
            }
            else
            {
                throw new WrongUsageException(getCommandUsage(sender));
            }
        }
        else
        {
            throw new WrongUsageException(getCommandUsage(sender));
        }
    }
}
