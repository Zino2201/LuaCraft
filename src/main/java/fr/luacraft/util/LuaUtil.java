package fr.luacraft.util;

import com.naef.jnlua.LuaState;

import java.io.*;

public class LuaUtil
{
    private static File runningScript;

    public static void runFromFile(LuaState l, File file)
    {
        try
        {
            runningScript = file;
            FileInputStream in = new FileInputStream(file);
            l.load(in, file.getPath(), "t");
            l.call(0, 0);
            in.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void runFromFile(LuaState l, File file, InputStream inputStream)
    {
        try
        {
            runningScript = file;
            l.load(inputStream, file.getPath(), "t");
            l.call(0, 0);
            inputStream.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static File getRunningScript()
    {
        return runningScript;
    }
}
