package fr.luacraft.core.api.blocks;

import fr.luacraft.core.api.ILuaObject;
import net.minecraftforge.common.IPlantable;

public class LuaIPlantable implements ILuaObject
{
    private IPlantable plantable;

    public LuaIPlantable(IPlantable plantable)
    {
        this.plantable = plantable;
    }

    @Override
    public String GetType()
    {
        return "Plantable";
    }

    @Override
    public boolean IsContainer()
    {
        return true;
    }

    @Override
    public Object GetContainedObject()
    {
        return plantable;
    }
}
