package fr.luacraft.core.command;

import fr.luacraft.core.Luacraft;
import fr.luacraft.modloader.LuacraftMod;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.util.ChatComponentText;

/**
 * Luacraft command
 * @author Zino
 */
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
            /*
            // TODO: Need a client command for that
            if(args[0].matches("console"))
            {
                Luacraft.getInstance().getProxy().openGUI(new GuiLuaConsole());
            }*/
            if(args[0].matches("help"))
            {
                sender.addChatMessage(new ChatComponentText("help"));
            }
            else if(args[0].matches("mods"))
            {
                StringBuilder str = new StringBuilder();
                str.append("Active mods (" + Luacraft.getInstance().getModLoader().getMods().size() + "): ");

                boolean first = false;

                for(LuacraftMod mod : Luacraft.getInstance().getModLoader().getMods())
                {
                    if(first)
                    {
                        str.append(", ");
                    }
                    else
                    {
                        first = true;
                    }

                    str.append(mod.getMetadata().name);
                }

                sender.addChatMessage(new ChatComponentText(str.toString()));
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
