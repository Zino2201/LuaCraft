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
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.common.MinecraftForge;
import org.lwjgl.util.Color;

/**
 * Client proxy
 * @author Zino
 */
public class ClientProxy extends SharedProxy
{
    public static final int MOD_LIST_BUTTON = 2201;

    public ClientProxy()
    {
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
            GuiButton luamodsbtn = new GuiLuaModMenuButton(
                    MOD_LIST_BUTTON,
                    event.gui.width - 20,
                    event.gui.height / 4 + 48 + 72 + 10);
            event.buttonList.add(luamodsbtn);
        }
    }

    @SubscribeEvent
    public void onGuiDrawScreen(GuiScreenEvent.DrawScreenEvent event)
    {
        if(event.gui instanceof GuiMainMenu)
        {
            String labelStr = String.format("Luacraft v%s", Luacraft.VERSION);
            Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(
                    labelStr,
                    event.gui.width - Minecraft.getMinecraft().fontRenderer.getStringWidth(labelStr) - 2,
                    event.gui.height - 30,
                    -1);

            String label2Str = String.format("%d lua mod%s loaded",
                    Luacraft.getInstance().getModLoader().mods.size(),
                    Luacraft.getInstance().getModLoader().mods.size() > 1 ? "s" : "");
            Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(
                    label2Str,
                    event.gui.width - Minecraft.getMinecraft().fontRenderer.getStringWidth(label2Str) - 2,
                    event.gui.height - 20,
                    -1);
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
