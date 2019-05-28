package fr.luacraft.api;

import fr.luacraft.modloader.ILuaContainer;
import fr.luacraft.modloader.ILuaContainerObject;
import fr.luacraft.modloader.LuaGameRegistry;
import fr.luacraft.modloader.LuacraftMod;

public class LuaMod implements ILuaContainer
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

    public void RegisterOre(String id, int dimensionID, int minY, int maxY, int veinSize, int chances)
    {
        mod.getRegistryData().addOre(id, dimensionID, minY, maxY, veinSize, chances);
    }

    @Override
    public String getType()
    {
        return "LuaMod";
    }

    @Override
    public ILuaContainerObject getContainedObject()
    {
        return mod;
    }
}
