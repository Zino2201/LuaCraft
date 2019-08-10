package fr.luacraft.core.proxy;

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
}
