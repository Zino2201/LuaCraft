package fr.luacraft.api;

import fr.luacraft.modloader.ILuaObject;
import fr.luacraft.modloader.LuaGameRegistry;
import fr.luacraft.modloader.LuacraftMod;

public class LuaMod implements ILuaObject
{
    private LuacraftMod mod;

    public LuaMod(LuacraftMod mod)
    {
        this.mod = mod;
    }

    public LuaBlock AddBlock(String id)
    {
        LuaBlock block = new LuaBlock(id);
        LuaGameRegistry.register(id, block.getContainedObject());
        return block;
    }

    public LuaItem AddItem(String id)
    {
        LuaItem item = new LuaItem(id);
        LuaGameRegistry.register(id, item.getContainedObject());
        return item;
    }

    @Override
    public String getType()
    {
        return "LuaMod";
    }
}
