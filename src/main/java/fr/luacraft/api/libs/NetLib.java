package fr.luacraft.api.libs;

import com.naef.jnlua.JavaFunction;
import com.naef.jnlua.LuaState;
import fr.luacraft.api.LuaEntityPlayer;
import fr.luacraft.core.LuaNetHookManager;
import fr.luacraft.core.network.LuacraftMessage;
import fr.luacraft.core.network.LuacraftPacketHandler;
import net.minecraft.entity.player.EntityPlayerMP;

/**
 * Network library
 * @author Zino
 */
public class NetLib
{
    private static String currentMessageName = "";

    // TODO: Write int, etc

    /**
     * Start a message
     */
    public static JavaFunction Start = new JavaFunction()
    {
        @Override
        public int invoke(LuaState l)
        {
            currentMessageName = l.checkString(1);

            return 0;
        }
    };

    /**
     * Send a message to the server
     */
    public static JavaFunction SendToServer = new JavaFunction()
    {
        @Override
        public int invoke(LuaState l)
        {
            LuacraftPacketHandler.INSTANCE.sendToServer(new LuacraftMessage(currentMessageName));

            return 0;
        }
    };

    /**
     * Send a message to a player
     */
    public static JavaFunction SendToPlayer = new JavaFunction()
    {
        @Override
        public int invoke(LuaState l)
        {
            LuaEntityPlayer player = l.checkJavaObject(1, LuaEntityPlayer.class);
            LuacraftPacketHandler.INSTANCE.sendTo(new LuacraftMessage(currentMessageName), (EntityPlayerMP) player.getEntityPlayer());

            return 0;
        }
    };

    /**
     * On receive hook
     */
    public static JavaFunction OnReceive = new JavaFunction()
    {
        @Override
        public int invoke(LuaState l)
        {
            String name = l.checkString(1);
            int func = l.ref(LuaState.REGISTRYINDEX);

            LuaNetHookManager.add(name, func);

            return 0;
        }
    };

    /**
     * Initialize the library
     * @param l
     */
    public static void Initialize(LuaState l)
    {
        /** Net table */
        l.newTable();
        l.pushJavaObject(Start);
        l.setField(-2, "Start");
        l.pushJavaObject(SendToServer);
        l.setField(-2, "SendToServer");
        l.pushJavaObject(SendToPlayer);
        l.setField(-2, "SendToPlayer");
        l.pushJavaObject(OnReceive);
        l.setField(-2, "OnReceive");
        l.setGlobal("net");
    }
}
