package fr.luacraft.core.network;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;

import java.util.Arrays;
import java.util.List;

/**
 * A luacraft message
 * @author Zino
 */
public class LuacraftMessage implements IMessage
{
    private String name;
    private int argsCount;
    private List<Object> args;

    public LuacraftMessage() { }

    public LuacraftMessage(String name, Object... params)
    {
        this.name = name;
        this.argsCount = params.length;
        this.args = Arrays.asList(params);
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        ByteBufUtils.writeUTF8String(buf, name);
        /*buf.writeInt(argsCount);
        for(Object object : args)
        {
            if(object instanceof Integer)
                buf.writeInt((Integer) object);

        }*/
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        name = ByteBufUtils.readUTF8String(buf);
    }

    public String getName()
    {
        return name;
    }
}
