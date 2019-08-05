package fr.luacraft.core.api.container;

import fr.luacraft.core.api.util.LuaClass;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

public class LuacraftContainer extends Container
{
    private LuaClass containerClass;

    public LuacraftContainer(LuaClass containerClass)
    {
        this.containerClass = containerClass;
    }

    @Override
    public boolean canInteractWith(EntityPlayer player)
    {
        return false;
    }
}
