package fr.luacraft.core;

import cpw.mods.fml.common.registry.GameData;
import cpw.mods.fml.common.registry.GameRegistry;
import fr.luacraft.api.LuaBlock;
import fr.luacraft.api.LuaItem;
import fr.luacraft.api.LuaObject;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

@Deprecated
public class LuaObjectManager
{
    public static HashMap<String, Class> classes = new HashMap<String, Class>();
    public static ArrayList<Object> objectsToRegister = new ArrayList<Object>();

    public static void registerClass(String name, Class clazz)
    {
        classes.put(name, clazz);
        Luacraft.getLogger().info("LuaObjectManager new class registered: " + name + " (" + clazz.getName() + ")");
    }

    public static String getTypeByObject(Object o)
    {
        if(o instanceof LuaObject)
            return ((LuaObject) o).getTypeName();

        return "Object";
    }

    public static<T> T createFromClass(String name, String id)
    {
        Class objectClass = classes.get(name);

        if(objectClass != null)
        {
            try
            {
                LuaObject object = (LuaObject) objectClass.getConstructor(String.class).newInstance(id);
                if(object instanceof LuaBlock)
                    GameRegistry.registerBlock(((LuaBlock) object).getBlock(), ((LuaBlock) object).getID());
                else if(object instanceof LuaItem)
                    GameRegistry.registerItem(((LuaItem) object).getItem(), ((LuaItem) object).getID());
                return (T) object;
            }
            catch (InstantiationException e)
            {
                e.printStackTrace();
            }
            catch (IllegalAccessException e)
            {
                e.printStackTrace();
            }
            catch (InvocationTargetException e)
            {
                e.printStackTrace();
            }
            catch (NoSuchMethodException e)
            {
                e.printStackTrace();
            }
        }

        return null;
    }

    public static void registerAll()
    {
        for(Object object : objectsToRegister)
        {

        }
    }
}
