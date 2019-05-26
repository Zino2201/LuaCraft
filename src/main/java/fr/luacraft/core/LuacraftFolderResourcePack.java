package fr.luacraft.core;

import cpw.mods.fml.client.FMLFolderResourcePack;
import cpw.mods.fml.common.ModContainer;

import java.util.Set;

/**
 * != .jar/.zip
 */
public class LuacraftFolderResourcePack extends FMLFolderResourcePack
{
    public LuacraftFolderResourcePack(ModContainer mod)
    {
        super(mod);
    }

    @Override
    public String getPackName()
    {
        return "LuacraftFolderResourcePack:" + getFMLContainer().getName();
    }
}
