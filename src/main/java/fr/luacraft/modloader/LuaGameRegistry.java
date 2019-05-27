package fr.luacraft.modloader;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.luacraft.api.classes.LuacraftBlock;
import fr.luacraft.api.classes.LuacraftItem;

public class LuaGameRegistry
{
    public static void register(String id, ILuaContainerObject object)
    {
        switch(object.getType())
        {
            case BLOCK:
                GameRegistry.registerBlock((LuacraftBlock) object, id);
                break;
            case ITEM:
                GameRegistry.registerItem((LuacraftItem) object, id);
                break;
        }
    }
}
