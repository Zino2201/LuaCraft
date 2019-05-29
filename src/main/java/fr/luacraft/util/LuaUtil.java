package fr.luacraft.util;

import com.naef.jnlua.LuaState;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

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

    public static File getRunningScript()
    {
        return runningScript;
    }
}
