package fr.luacraft.core.api.blocks;

import com.naef.jnlua.util.LuaFunction;
import fr.luacraft.core.api.ILuaObject;
import fr.luacraft.core.api.reflection.LuaJavaObject;
import net.minecraftforge.common.IPlantable;

public class LuaIPlantable implements ILuaObject
{
    private IPlantable plantable;

    public LuaIPlantable(IPlantable plantable)
    {
        this.plantable = plantable;
    }

    @Override
    @LuaFunction
    public String GetType()
    {
        return "Plantable";
    }

    @Override
    @LuaFunction
    public boolean IsContainer()
    {
        return true;
    }

    @Override
    @LuaFunction
    public LuaJavaObject GetContainedObject()
    {
        return new LuaJavaObject(plantable);
    }
}
