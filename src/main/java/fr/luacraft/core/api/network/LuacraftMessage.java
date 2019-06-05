package fr.luacraft.core.api.network;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;

import java.io.*;
import java.util.ArrayList;
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

    public LuacraftMessage(String name, List<Object> args)
    {
        this.name = name;
        this.argsCount = args.size();
        this.args = args;
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        ByteBufUtils.writeUTF8String(buf, name);
        buf.writeInt(argsCount);
        for(Object object : args)
        {
            byte[] bytes = serializableObjectToBytes(object);
            buf.writeInt(bytes.length);
            buf.writeBytes(bytes);
        }
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        name = ByteBufUtils.readUTF8String(buf);
        argsCount = buf.readInt();
        args = new ArrayList<Object>(argsCount);
        for(int i = 0; i < argsCount; i++)
        {
            byte[] bytes = new byte[buf.readInt()];
            buf.readBytes(bytes);
            args.add(bytesToSerializableObject(bytes));
        }
    }

    public String getName()
    {
        return name;
    }

    public int getArgsCount()
    {
        return argsCount;
    }

    public List<Object> getArgs()
    {
        return args;
    }

    public static byte[] serializableObjectToBytes(Object object)
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutput out = null;

        try
        {
            out = new ObjectOutputStream(bos);
            out.writeObject(object);
            out.flush();
            return bos.toByteArray();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                bos.close();
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }

        return null;
    }

    public static Object bytesToSerializableObject(byte[] bytes)
    {
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        ObjectInput in = null;
        try
        {
            in = new ObjectInputStream(bis);
            return in.readObject();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (in != null)
                {
                    in.close();
                }
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }

        return null;
    }
}
