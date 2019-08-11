package fr.luacraft.core.api.creativetab;

import fr.luacraft.core.api.ILuaObject;
import fr.luacraft.core.api.reflection.LuaJavaObject;
import net.minecraft.creativetab.CreativeTabs;

public class LuaCreativeTab implements ILuaObject
{
    private CreativeTabs creativeTabs;

    public LuaCreativeTab(CreativeTabs creativeTabs)
    {
        this.creativeTabs = creativeTabs;
    }

    public CreativeTabs getCreativeTabs()
    {
        return creativeTabs;
    }

    @Override
    public String GetType()
    {
        return "CreativeTab";
    }

    @Override
    public boolean IsContainer()
    {
        return true;
    }

    @Override
    public LuaJavaObject GetContainedObject()
    {
        return new LuaJavaObject(creativeTabs);
    }
}
