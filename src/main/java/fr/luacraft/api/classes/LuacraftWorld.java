package fr.luacraft.api.classes;

import fr.luacraft.modloader.ContainerObjectType;
import fr.luacraft.modloader.ILuaContainerObject;
import net.minecraft.world.World;

public class LuacraftWorld implements ILuaContainerObject
{
    private World world;

    public LuacraftWorld(World world)
    {
        this.world = world;
    }

    @Override
    public ContainerObjectType getType()
    {
        return ContainerObjectType.WORLD;
    }

    public World getWorld()
    {
        return world;
    }
}
