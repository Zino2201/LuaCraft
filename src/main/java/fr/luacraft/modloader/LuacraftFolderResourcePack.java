package fr.luacraft.modloader;

import cpw.mods.fml.client.FMLFolderResourcePack;
import cpw.mods.fml.common.ModContainer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Luacraft folder resource pack
 * Used for mod that loaded from a directory
 * @author Zino
 */
public class LuacraftFolderResourcePack extends FMLFolderResourcePack
{
    private LuacraftModContainer lcmc;

    public LuacraftFolderResourcePack(ModContainer mod)
    {
        super(mod);

        if(mod instanceof LuacraftModContainer)
            this.lcmc = (LuacraftModContainer) mod;
    }

    @Override
    public String getPackName()
    {
        return "LuacraftFolderResourcePack:" + getFMLContainer().getName();
    }

    @Override
    public BufferedImage getPackImage() throws IOException
    {
        return ImageIO.read(getInputStreamByName(lcmc.getModInfo().getLogo()));
    }
}
