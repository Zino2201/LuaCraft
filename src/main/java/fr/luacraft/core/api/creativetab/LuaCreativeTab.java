package fr.luacraft.core.api.creativetab;

import fr.luacraft.core.api.ILuaObject;
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
    public boolean isContainer()
    {
        return true;
    }

    @Override
    public Object getObject()
    {
        return creativeTabs;
    }
}
