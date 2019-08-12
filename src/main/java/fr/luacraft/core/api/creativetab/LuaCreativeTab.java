package fr.luacraft.core.api.creativetab;

import fr.luacraft.core.api.ILuaContainer;
import fr.luacraft.core.api.reflection.LuaJavaObject;
import net.minecraft.creativetab.CreativeTabs;

public class LuaCreativeTab implements ILuaContainer
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
    public String GetTypeName()
    {
        return "CreativeTab";
    }

    @Override
    public LuaJavaObject GetContainedObject()
    {
        return new LuaJavaObject(creativeTabs);
    }
}
