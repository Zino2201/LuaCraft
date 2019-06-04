package fr.luacraft.core.network;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import fr.luacraft.core.Luacraft;

/**
 * Luacraft network handler, for manage packets and other network things
 * @author Zino
 */
public class LuacraftPacketHandler
{
    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Luacraft.MODID);

    /**
     * Register all messages
     */
    public static void registerMessages()
    {
        int discriminator = 0;
        INSTANCE.registerMessage(LuacraftMessageHandler.class, LuacraftMessage.class, discriminator++, Side.SERVER);
        INSTANCE.registerMessage(LuacraftMessageHandler.class, LuacraftMessage.class, discriminator++, Side.CLIENT);
    }
}
