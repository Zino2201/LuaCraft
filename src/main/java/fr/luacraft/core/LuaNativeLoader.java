package fr.luacraft.core;

import com.naef.jnlua.NativeSupport;
import org.apache.commons.lang3.SystemUtils;

import java.io.*;

public class LuaNativeLoader implements NativeSupport.Loader
{
    private String nativeDir = "natives/";

    @Override
    public void load()
    {
        // TODO: Implement OSX and Linux

        boolean intelij = false;
        if(isInDevEnvironnement()) {
            nativeDir = "D:\\Projects\\LuaCraft\\src\\main\\resources\\natives\\";
            intelij = true;
        }

        if(SystemUtils.IS_OS_WINDOWS)
        {
            File tmpDir = new File(System.getProperty("java.io.tmpdir") + "/Luacraft/");
            if(!tmpDir.exists())
                tmpDir.mkdirs();

            if(!intelij)
            {
                extractFile(nativeDir + "lua52.dll", System.getProperty("java.io.tmpdir") + "Luacraft/" + "lua52.dll");
                extractFile(nativeDir + "jnlua52.dll", System.getProperty("java.io.tmpdir") + "Luacraft/" + "jnlua52.dll");


                System.load(System.getProperty("java.io.tmpdir") + "/Luacraft/" + "lua52.dll");
                System.load(System.getProperty("java.io.tmpdir") + "/Luacraft/" + "jnlua52.dll");
            }else
            {
                System.load(nativeDir + "lua52.dll");
                System.load(nativeDir + "jnlua52.dll");
            }
            // TODO: Delete temps
        }
        else
        {
            Luacraft.getLogger().fatal("Windows is the only supported os at the moment");
        }
    }

    public static File extractFile(String strFrom, String strTo)
    {
        File extractedFile = new File(strTo);
        int lastSlash = extractedFile.toString().lastIndexOf(File.separator);
        String filePath = extractedFile.toString().substring(0, lastSlash);

        File fileDirectory = new File(filePath);
        if (!fileDirectory.exists())
            fileDirectory.mkdirs();

        int readBytes;
        byte[] buffer = new byte[1024];

        InputStream fileInStream = null;
        OutputStream fileOutStream = null;

        try
        {
            fileInStream = getPackedFileInputStream(strFrom);
            fileOutStream = new FileOutputStream(extractedFile);

            while ((readBytes = fileInStream.read(buffer)) != -1)
                fileOutStream.write(buffer, 0, readBytes);
        }
        catch (Exception e)
        {
            throw new Error(e.getMessage());
        }
        finally
        {
            try
            {
                fileOutStream.close();
                fileInStream.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

        }

        return extractedFile;
    }

    public static InputStream getPackedFileInputStream(String file) throws FileNotFoundException
    {
        InputStream in = null;
        in = Luacraft.class.getResourceAsStream('/' + file);

        return in;
    }

    public static boolean isInDevEnvironnement()
    {
        // TODO: Detect eclipse?

        String classPath = System.getProperty("java.class.path");
        return classPath.contains("idea_rt.jar");
    }
}
