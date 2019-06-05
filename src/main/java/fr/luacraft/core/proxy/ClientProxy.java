package fr.luacraft.core.proxy;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import fr.luacraft.core.api.libs.GuiLib;
import fr.luacraft.core.api.libs.I18NLib;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

/**
 * Client proxy
 * @author Zino
 */
public class ClientProxy extends SharedProxy
{
    public ClientProxy()
    {
        this.scriptPrefix = "cl";
    }

    @Override
    public void preInit(FMLPreInitializationEvent event)
    {
        GuiLib.initialize(luaState);
        I18NLib.initialize(luaState);

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

    @Override
    public void init(FMLInitializationEvent event)
    {
        super.init(event);
    }
}
