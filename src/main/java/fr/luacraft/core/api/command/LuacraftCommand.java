package fr.luacraft.core.api.command;

import fr.luacraft.core.api.hooks.LuaHookManager;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

/**
 * A luacraft command
 * @author Zino
 */
public class LuacraftCommand extends CommandBase
{
    private String name;
    private String usage;
    private int requiredPermissionLevel;

    public LuacraftCommand(String name, String usage)
    {
        this.name = name;
        this.usage = usage;
        this.requiredPermissionLevel = 0;
    }

    public void setRequiredPermissionLevel(int requiredPermissionLevel)
    {
        this.requiredPermissionLevel = requiredPermissionLevel;
    }

    @Override
    public String getCommandName()
    {
        return name;
    }

    @Override
    public String getCommandUsage(ICommandSender sender)
    {
        return usage;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args)
    {
        // TODO: Sender, args

        LuaHookManager.call(this, "OnCalled", new LuaCommandSender(sender), args);
    }

    @Override
    public int getRequiredPermissionLevel()
    {
        return requiredPermissionLevel;
    }
}
