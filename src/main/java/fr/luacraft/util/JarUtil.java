package fr.luacraft.util;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Jar utils functions
 * @author Zino
 */
public class JarUtil
{
    /**
     * Extract a jar file to a directory
     * @param dest
     * @param src
     * @throws IOException
     */
    public static void extractResourceFile(File dest, String src) throws IOException
    {
        InputStream inputStream = JarUtil.class.getResourceAsStream("/" + src);
        FileUtils.copyInputStreamToFile(inputStream, dest);
    }
}
