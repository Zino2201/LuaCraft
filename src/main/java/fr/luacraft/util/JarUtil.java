package fr.luacraft.util;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.jar.JarFile;

public class JarUtil
{
    public static JarFile getCurrent(Object instance)
    {
        JarFile jarFile = null;

        try
        {
            String s = new File(instance.getClass().getResource("").getPath()).getParent().replaceAll("(!|file:\\\\)",
                    "");
            jarFile = new JarFile(s);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        return jarFile;
    }

    public static void extractResourceFile(File dest, String src) throws IOException
    {
        InputStream inputStream = JarUtil.class.getResourceAsStream("/" + src);
        FileUtils.copyInputStreamToFile(inputStream, dest);
    }
}
