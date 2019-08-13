package fr.luacraft.core.api.libs;

import com.naef.jnlua.JavaFunction;
import com.naef.jnlua.LuaState;
import fr.luacraft.core.LuaNativeLoader;
import fr.luacraft.core.Luacraft;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Provide safe IO capabilities for mods
 * @author Zino
 */
@LuaLibrary
public class FileLib
{
    public static final String[] BLACKLISTED_STRINGS = {
            "*",
            "?",
            "*",
            "<",
            ">",
            "|",
            ":",
            "../",
            "..",
            "..\\"
    };

    public static final String BASE_LUACRAFT_FILE_PATH = "mods/" + Luacraft.TARGET_MC_VERSION + "/files/";

    public static JavaFunction Write = new JavaFunction()
    {
        @Override
        public int invoke(LuaState l)
        {
            String path = l.checkString(1);
            String content = l.checkString(2);

            boolean succeed = true;
            try
            {
                String safePath = getSafePath(path);

                int i = safePath.lastIndexOf(File.separator);
                if(i <= -1)
                    i = safePath.lastIndexOf('/');

                File dir = new File(safePath.substring(0, i));
                if (!dir.exists())
                    dir.mkdirs();
                if(!Files.exists(Paths.get(safePath)))
                    Files.createFile(Paths.get(safePath));
                Files.write(Paths.get(safePath), content.getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
            }
            catch (IOException e)
            {
                e.printStackTrace();
                succeed = false;
            }

            l.pushBoolean(succeed);

            return 1;
        }
    };

    public static JavaFunction Append = new JavaFunction()
    {
        @Override
        public int invoke(LuaState l)
        {
            String path = l.checkString(1);
            String content = l.checkString(2);

            boolean succeed = true;

            String safePath = getSafePath(path);

            Path filePath = Paths.get(safePath);
            boolean fileExists = Files.exists(filePath);

            try
            {
                if (fileExists)
                {
                    Files.write(filePath, content.getBytes(), StandardOpenOption.APPEND);
                }
                else
                    {
                    throw new FileNotFoundException(String.format("%s not found"));
                }
            }
            catch(IOException e)
            {
                e.printStackTrace();
                succeed = false;
            }

            l.pushBoolean(succeed);

            return 1;
        }
    };

    public static JavaFunction Read = new JavaFunction()
    {
        @Override
        public int invoke(LuaState l)
        {
            String path = l.checkString(1);

            String string = null;

            String safePath = getSafePath(path);

            Path filePath = Paths.get(safePath);
            boolean fileExists = Files.exists(filePath);

            try
            {
                if (fileExists)
                {
                    string = new String(Files.readAllBytes(filePath));
                }
                else
                {
                    throw new FileNotFoundException(String.format("%s not found"));
                }
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }

            l.pushString(string);

            return 1;
        }
    };

    public static JavaFunction Exists = new JavaFunction()
    {
        @Override
        public int invoke(LuaState l)
        {
            String path = l.checkString(1);
            String safePath = getSafePath(path);

            Path filePath = Paths.get(safePath);
            boolean fileExists = Files.exists(filePath);

            l.pushBoolean(fileExists);

            return 1;
        }
    };

    public static String getSafePath(String path)
    {
        StringBuilder retPath = new StringBuilder();

        retPath.append(BASE_LUACRAFT_FILE_PATH);
        retPath.append(Luacraft.getInstance().getModLoader().getCurrentMod().getModId());
        retPath.append("/");
        for(String blStr : BLACKLISTED_STRINGS)
        {
            if (path.contains(blStr))
                path = path.replace(blStr, "");
        }
        retPath.append(path);

        String retString = retPath.toString();
        if(LuaNativeLoader.isInDevEnvironnement())
            retString = retString.replace(Luacraft.TARGET_MC_VERSION, "devenv");

        return retString;
    }

    public static void initialize(LuaState l)
    {
        l.newTable();
        l.pushJavaFunction(Write);
        l.setField(-2, "Write");
        l.pushJavaFunction(Append);
        l.setField(-2, "Append");
        l.pushJavaFunction(Read);
        l.setField(-2, "Read");
        l.pushJavaFunction(Exists);
        l.setField(-2, "Exists");
        l.setGlobal("file");
    }
}
