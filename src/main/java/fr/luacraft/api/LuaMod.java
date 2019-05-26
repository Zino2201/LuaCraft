package fr.luacraft.api;

import cpw.mods.fml.common.LoadController;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameData;
import cpw.mods.fml.common.registry.GameRegistry;
import fr.luacraft.classes.LuacraftBlock;
import fr.luacraft.core.LuaObjectManager;
import fr.luacraft.core.LuacraftMod;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Method;

public class LuaMod extends LuaObject
{
    private LuacraftMod mod;

    public LuaMod(String id)
    {
        super(id);
    }

    public LuaMod(LuacraftMod mod)
    {
        super(mod.getModId());

        this.mod = mod;
    }

    public LuaBlock AddBlock(String id)
    {
        return LuaObjectManager.createFromClass("Block", id);
    }

    @Override
    public String getTypeName()
    {
        return "LuaMod";
    }
}
