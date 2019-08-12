package fr.luacraft.core.gui;

import com.google.common.base.Joiner;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.ModContainer;
import fr.luacraft.core.Luacraft;
import fr.luacraft.modloader.LuacraftMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

/**
 * Lua mod menu
 * It is just cpw's GuiModList but modified a bit...
 */
public class GuiLuaModMenu extends GuiScreen
{
    private GuiScreen mainMenu;
    private GuiLuaModList modList;
    private int currentModIndex = -1;
    private LuacraftMod currentMod;
    private int listWidth;
    private ResourceLocation cachedLogo;
    private Dimension cachedLogoDimensions;

    public GuiLuaModMenu(GuiScreen mainMenu)
    {
        this.mainMenu = mainMenu;
    }

    @Override
    public void initGui()
    {
        listWidth = 125;

        modList = new GuiLuaModList(this, listWidth);
        modList.registerScrollButtons(this.buttonList, 7, 8);

        this.buttonList.add(new GuiButton(2201+1, this.width / 2 - 75, this.height - 38, I18n.format("gui.done")));
    }

    @Override
    protected void actionPerformed(GuiButton button)
    {
        if (button.enabled)
        {
            switch (button.id)
            {
                case 2201+1:
                    this.mc.displayGuiScreen(this.mainMenu);
                    return;
            }
        }

        super.actionPerformed(button);
    }

    @Override
    public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_)
    {
        this.drawDefaultBackground();

        this.drawCenteredString(this.fontRendererObj, "Lua mod list", this.width / 2, 16, 0xFFFFFF);

        modList.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);

        if(currentMod != null)
        {
            int shifty = 35;
            int offset = listWidth + 20;

            String logoFile = currentMod.getModInfo().getLogo();
            if (!logoFile.isEmpty())
            {
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                TextureManager tm = mc.getTextureManager();
                IResourcePack pack = FMLClientHandler.instance().getResourcePackFor(currentMod.getModId());
                try
                {
                    if (cachedLogo == null)
                    {
                        BufferedImage logo = null;
                        if (pack!=null)
                        {
                            logo = pack.getPackImage();
                        }
                        else
                        {
                            InputStream logoResource = getClass().getResourceAsStream(logoFile);
                            if (logoResource != null)
                            {
                                logo = ImageIO.read(logoResource);
                            }
                        }
                        if (logo != null)
                        {
                            cachedLogo = tm.getDynamicTextureLocation("modlogo", new DynamicTexture(logo));
                            cachedLogoDimensions = new Dimension(logo.getWidth(), logo.getHeight());
                        }
                    }
                    if (cachedLogo != null)
                    {
                        this.mc.renderEngine.bindTexture(cachedLogo);
                        double scaleX = cachedLogoDimensions.width / 200.0;
                        double scaleY = cachedLogoDimensions.height / 65.0;
                        double scale = 1.0;
                        if (scaleX > 1 || scaleY > 1)
                        {
                            scale = 1.0 / Math.max(scaleX, scaleY);
                        }
                        cachedLogoDimensions.width *= scale;
                        cachedLogoDimensions.height *= scale;
                        int top = 32;
                        Tessellator tess = Tessellator.instance;
                        tess.startDrawingQuads();
                        tess.addVertexWithUV(offset,                               top + cachedLogoDimensions.height,  zLevel, 0, 1);
                        tess.addVertexWithUV(offset + cachedLogoDimensions.width,  top + cachedLogoDimensions.height,  zLevel, 1, 1);
                        tess.addVertexWithUV(offset + cachedLogoDimensions.width,  top,                                zLevel, 1, 0);
                        tess.addVertexWithUV(offset,                               top,                                zLevel, 0, 0);
                        tess.draw();

                        shifty += 65;
                    }
                }
                catch (IOException e)
                {
                    ;
                }
            }

            fontRendererObj.drawStringWithShadow(String.format("%s v%s (%s)",
                    currentMod.getName(), currentMod.getVersion(), currentMod.getModId()), offset, shifty, 0xFFFFFF);
            shifty += 12;
            shifty = drawLine("Made for Luacraft v" + currentMod.getLuacraftVersion(), offset, shifty);
            if(currentMod.isObsolete())
            {
                fontRendererObj.drawSplitString(String.format("Warning: Your version of Luacraft is not " +
                                "supported by this mod. (supported: %s, current: %s)",
                                currentMod.getLuacraftVersion(), Luacraft.VERSION),
                        offset, shifty, width - offset - 20, 0xFF0000);
                shifty += 25;
            }
            shifty = drawLine(String.format("Author(s): %s", Joiner.on(", ").join(currentMod.getModInfo().getAuthors())), offset, shifty);
            shifty += 10;
            shifty = drawLine(String.format("See also %s", currentMod.getModInfo().getWebsite()), offset, shifty);

            int rightSide = this.width - offset - 20;
            if (rightSide > 20)
            {
                shifty += 10;
                fontRendererObj.drawSplitString(currentMod.getModInfo().getDescription(), offset,
                        shifty, rightSide, 0xDDDDDD);
            }

        }

        super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
    }

    public void selectMod(int mod)
    {
        currentModIndex = mod;
        currentMod = Luacraft.getInstance().getModLoader().getMods().get(mod);
        cachedLogo = null;
        cachedLogoDimensions = null;
    }

    public boolean isSelected(int mod)
    {
        return currentModIndex == mod;
    }

    public int drawLine(String line, int offset, int shifty, int color)
    {
        this.fontRendererObj.drawString(line, offset, shifty, color);
        return shifty + 10;
    }

    public int drawLine(String line, int offset, int shifty)
    {
        return drawLine(line, offset, shifty, 0xd7edea);
    }
}


