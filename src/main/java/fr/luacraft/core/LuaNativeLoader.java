package fr.luacraft.core;

import com.naef.jnlua.NativeSupport;
import fr.luacraft.util.JarUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.SystemUtils;

import java.io.File;
import java.io.IOException;

public class LuaNativeLoader implements NativeSupport.Loader
{
    private String nativeDir = "natives/";
    private String jnlua = "jnlua52";
    private String lua = "lua52";
    private String ext = ".dll";
    private File tmpDir;

    @Override
    public void load()
    {
        // TODO: Implement OSX and Linux

        if(isInDevEnvironnement())
            nativeDir = "D:\\Projects\\LuaCraft\\src\\main\\resources\\natives\\";

        if(SystemUtils.IS_OS_WINDOWS)
        {
            ext = ".dll";
        }
        else
        {
            Luacraft.getLogger().fatal("Windows is the only supported os at the moment");
        }

        if(isInDevEnvironnement())
        {
            System.load(nativeDir + "lua52.dll");
            System.load(nativeDir + "jnlua52.dll");
        }
        else
        {
            tmpDir = new File(System.getProperty("java.io.tmpdir") + "/Luacraft/");
            if(!tmpDir.exists())
                tmpDir.mkdirs();

            try
            {
                JarUtil.extractResourceFile(new File(tmpDir, lua + ext), nativeDir + lua + ext);
                JarUtil.extractResourceFile(new File(tmpDir, jnlua + ext), nativeDir + jnlua + ext);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

            System.load(new File(tmpDir, lua + ext).getPath());
            System.load(new File(tmpDir, jnlua + ext).getPath());
        }
    }

    @Override
    public void exit()
    {
        if(isInDevEnvironnement())
            return;

        try
        {
            FileUtils.forceDeleteOnExit(tmpDir);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static boolean isInDevEnvironnement()
    {
        // TODO: Detect eclipse because this code only detect InteliJ
        String classPath = System.getProperty("java.class.path");
        return classPath.contains("idea_rt.jar");
    }
}
