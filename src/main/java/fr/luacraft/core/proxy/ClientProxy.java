package fr.luacraft.core.proxy;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import fr.luacraft.core.Luacraft;
import fr.luacraft.core.api.libs.LuaLibrary;
import fr.luacraft.core.gui.GuiLuaModMenu;
import fr.luacraft.core.gui.GuiLuaModMenuButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.common.MinecraftForge;

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
    public void setupLua()
    {
        super.setupLua();

        /** Load client libraries annotated with {@link LuaLibrary} */
        Luacraft.getLogger().info("Registering client libraries...");
        Luacraft.getLogger().info(registerLibraries(ProxyType.CLIENT) + " client libraries registered");
    }

    @Override
    public void preInit(FMLPreInitializationEvent event)
    {
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
                    (GuiMainMenu) event.gui,
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
