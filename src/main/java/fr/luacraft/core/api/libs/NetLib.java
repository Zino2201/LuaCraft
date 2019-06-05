package fr.luacraft.core.api.libs;

import com.naef.jnlua.JavaFunction;
import com.naef.jnlua.LuaState;
import fr.luacraft.core.api.entity.LuaEntityPlayer;
import fr.luacraft.core.api.hooks.LuaNetworkHookManager;
import fr.luacraft.core.api.network.LuacraftMessage;
import fr.luacraft.core.api.network.LuacraftPacketHandler;
import net.minecraft.entity.player.EntityPlayerMP;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Network library
 * @author Zino
 */
public class NetLib
{
    private static String currentMessageName = "";
    private static ArrayList<Object> currentMessageData = new ArrayList<Object>();

    /**
     * Start a message
     */
    public static JavaFunction Start = new JavaFunction()
    {
        @Override
        public int invoke(LuaState l)
        {
            currentMessageName = l.checkString(1);
            currentMessageData.clear();

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
            LuacraftPacketHandler.INSTANCE.sendToServer(new LuacraftMessage(currentMessageName, currentMessageData));

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
            LuacraftPacketHandler.INSTANCE.sendTo(new LuacraftMessage(currentMessageName, currentMessageData), (EntityPlayerMP) player.getEntityPlayer());

            return 0;
        }
    };

    /**
     * Send a message to all players
     */
    public static JavaFunction SendToAll = new JavaFunction()
    {
        @Override
        public int invoke(LuaState l)
        {
            LuacraftPacketHandler.INSTANCE.sendToAll(new LuacraftMessage(currentMessageName, currentMessageData));

            return 0;
        }
    };

    /**
     * Send a message to all players
     */
    public static JavaFunction SendToAllInDimension = new JavaFunction()
    {
        @Override
        public int invoke(LuaState l)
        {
            int dimID = l.checkInteger(1);
            LuacraftPacketHandler.INSTANCE.sendToDimension(new LuacraftMessage(currentMessageName, currentMessageData), dimID);

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

            LuaNetworkHookManager.add(name, func);

            return 0;
        }
    };

    /**
     * Write
     */
    public static JavaFunction Write = new JavaFunction()
    {
        @Override
        public int invoke(LuaState l)
        {
            if(l.isNumber(1))
                currentMessageData.add(l.checkNumber(1));
            else if(l.isString(1))
                currentMessageData.add(l.checkString(1));
            else if(l.isBoolean(1))
                currentMessageData.add(l.toBoolean(1));
            else if(l.isJavaObject(1, Serializable.class))
                currentMessageData.add(l.toJavaObject(1, Serializable.class));

            return 0;
        }
    };

    /**
     * initialize the library
     * @param l
     */
    public static void initialize(LuaState l)
    {
        /** Net table */
        l.newTable();
        l.pushJavaObject(Start);
        l.setField(-2, "Start");
        l.pushJavaObject(SendToServer);
        l.setField(-2, "SendToServer");
        l.pushJavaObject(SendToPlayer);
        l.setField(-2, "SendToPlayer");
        l.pushJavaObject(SendToAll);
        l.setField(-2, "SendToAll");
        l.pushJavaObject(SendToAllInDimension);
        l.setField(-2, "SendToAllInDimension");
        l.pushJavaObject(OnReceive);
        l.setField(-2, "OnReceive");
        l.pushJavaObject(Write);
        l.setField(-2, "Write");
        l.setGlobal("net");
    }
}
