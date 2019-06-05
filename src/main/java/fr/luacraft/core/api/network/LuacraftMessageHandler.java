package fr.luacraft.core.api.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import fr.luacraft.core.api.hooks.LuaNetworkHookManager;

/**
 * Luacraft message handler
 * @author Zino
 */
public class LuacraftMessageHandler implements IMessageHandler<LuacraftMessage, IMessage>
{
    @Override
    public IMessage onMessage(LuacraftMessage message, MessageContext ctx)
    {
        LuaNetworkHookManager.call(message.getName(), message.getArgs().toArray());
        return null;
    }
}
