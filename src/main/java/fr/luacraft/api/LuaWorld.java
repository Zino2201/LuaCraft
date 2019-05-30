package fr.luacraft.api;

import fr.luacraft.api.classes.LuacraftWorld;
import fr.luacraft.modloader.ILuaContainer;
import fr.luacraft.modloader.ILuaContainerObject;

public class LuaWorld implements ILuaContainer
{
    private LuacraftWorld world;

    public LuaWorld(LuacraftWorld world)
    {
        this.world = world;
    }

    @Override
    public ILuaContainerObject getContainedObject()
    {
        return world;
    }

    @Override
    public String getType()
    {
        return "World";
    }
}
