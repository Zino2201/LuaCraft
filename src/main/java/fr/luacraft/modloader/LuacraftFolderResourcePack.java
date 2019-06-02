package fr.luacraft.modloader;

import cpw.mods.fml.client.FMLFolderResourcePack;
import cpw.mods.fml.common.ModContainer;

/**
 * Luacraft folder resource pack
 * Used for mod that loaded from a directory
 * @author Zino
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
