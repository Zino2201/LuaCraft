package fr.luacraft.core.api.command;

import com.naef.jnlua.util.LuaFunction;
import fr.luacraft.core.api.ILuaContainer;
import fr.luacraft.core.api.reflection.LuaJavaObject;
import net.minecraft.command.CommandBase;

public class LuaCommand implements ILuaContainer
{
    private CommandBase commandBase;

    public LuaCommand(CommandBase commandBase)
    {
        this.commandBase = commandBase;
    }

    @LuaFunction
    public void SetRequiredPermissionLevel(int level)
    {
        if(commandBase instanceof LuacraftCommand)
            ((LuacraftCommand) commandBase).setRequiredPermissionLevel(level);
    }

    @Override
    @LuaFunction
    public String GetTypeName()
    {
        return "CommandBase";
    }

    @Override
    @LuaFunction
    public LuaJavaObject GetContainedObject()
    {
        return new LuaJavaObject(commandBase);
    }
}
