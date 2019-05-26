package fr.luacraft.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

/**
 * Client proxy
 */
public class ClientProxy extends SharedProxy
{
    public ClientProxy()
    {
        this.scriptPrefix = "cl";
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
}
