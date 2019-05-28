package fr.luacraft.util;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class JarUtil
{
    public static void extractResourceFile(File dest, String src) throws IOException
    {
        InputStream inputStream = JarUtil.class.getResourceAsStream("/" + src);
        if(inputStream == null)
            System.err.println("is was null");
        FileUtils.copyInputStreamToFile(inputStream, dest);
    }
}
