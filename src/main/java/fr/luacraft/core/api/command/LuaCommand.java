package fr.luacraft.core.api.command;

import fr.luacraft.core.api.ILuaObject;
import net.minecraft.command.CommandBase;

public class LuaCommand implements ILuaObject
{
    private CommandBase commandBase;

    public LuaCommand(CommandBase commandBase)
    {
        this.commandBase = commandBase;
    }

    public void SetRequiredPermissionLevel(int level)
    {
        if(commandBase instanceof LuacraftCommand)
            ((LuacraftCommand) commandBase).setRequiredPermissionLevel(level);
    }

    @Override
    public String GetType()
    {
        return "CommandBase";
    }

    @Override
    public boolean isContainer()
    {
        return true;
    }

    @Override
    public Object getObject()
    {
        return commandBase;
    }
}
