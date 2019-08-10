package fr.luacraft.core.proxy;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.luacraft.core.Luacraft;
import fr.luacraft.core.api.libs.GuiLib;
import fr.luacraft.core.api.libs.I18NLib;
import fr.luacraft.core.gui.GuiLuaModMenu;
import fr.luacraft.core.gui.GuiLuaModMenuButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraft.util.MathHelper;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Color;

import javax.annotation.Nonnull;

/**
 * Client proxy
 * @author Zino
 */
public class ClientProxy extends SharedProxy
{
    public static final int MOD_LIST_BUTTON = 2201;

    public ClientProxy()
    {
        this.type = ProxyType.CLIENT;
        this.scriptPrefix = "cl";
    }

    @Override
    public void preInit(FMLPreInitializationEvent event)
    {
        GuiLib.initialize(luaState);
        I18NLib.initialize(luaState);

        MinecraftForge.EVENT_BUS.register(this);

        super.preInit(event);
    }

    /**
     * Open a GUI
     * @param screen
     */
    @Override
    public void openGUI(GuiScreen screen)
    {
        super.openGUI(screen);

        Minecraft.getMinecraft().displayGuiScreen(screen);
    }

    @SubscribeEvent
    public void onGuiInit(GuiScreenEvent.InitGuiEvent event)
    {
        if(event.gui instanceof GuiMainMenu)
        {
            int i = event.gui.height / 4 + 48;

            GuiButton luamodsbtn = new GuiLuaModMenuButton(
                    MOD_LIST_BUTTON,
                    event.gui.width / 2 - 100,
                    i + 24 * 2);
                    //event.gui.width - 20,
                    //event.gui.height / 4 + 48 + 72 + 10);
            luamodsbtn.xPosition = (event.gui.width / 2 + 2) + 100;
            event.buttonList.add(luamodsbtn);
        }
    }

    @SubscribeEvent
    public void onGuiDrawScreen(GuiScreenEvent.DrawScreenEvent event)
    {
        if(event.gui instanceof GuiMainMenu)
        {
            if (Luacraft.getInstance().getModLoader().getObsoleteMods().size() > 0) {
                int i = event.gui.height / 4 + 48;

                int x = (event.gui.width / 2 + 2) + 115;
                int y = (i + 24 * 2) + 12;

                Minecraft.getMinecraft().fontRenderer.drawStringWithShadow("!", x,
                        y, 0xFF0000);
            }
        }
    }

    @SubscribeEvent
    public void onGuiActionPerformed(GuiScreenEvent.ActionPerformedEvent event)
    {
        if(event.gui instanceof GuiMainMenu)
        {
            if (event.button.id == MOD_LIST_BUTTON)
            {
                Minecraft.getMinecraft().displayGuiScreen(new GuiLuaModMenu(event.gui));
            }
        }
    }
}
