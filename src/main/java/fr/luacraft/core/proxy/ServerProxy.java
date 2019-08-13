package fr.luacraft.core.proxy;

import fr.luacraft.core.Luacraft;

/**
 * Server proxy
 * @author Zino
 */
public class ServerProxy extends SharedProxy
{
    public ServerProxy()
    {
        this.type = ProxyType.SERVER;
        this.scriptPrefix = "sv";
    }

    @Override
    public void setupLua()
    {
        super.setupLua();

        Luacraft.getLogger().info("Registering server libraries...");
        Luacraft.getLogger().info(registerLibraries(ProxyType.SERVER) + " server libraries registered");
    }
}