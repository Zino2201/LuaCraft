package fr.luacraft.core.api.blocks;

import com.naef.jnlua.util.LuaFunction;
import fr.luacraft.core.api.ILuaContainer;
import fr.luacraft.core.api.reflection.LuaJavaObject;
import net.minecraftforge.common.IPlantable;

public class LuaIPlantable implements ILuaContainer
{
    private IPlantable plantable;

    public LuaIPlantable(IPlantable plantable)
    {
        this.plantable = plantable;
    }

    @Override
    @LuaFunction
    public String GetTypeName()
    {
        return "Plantable";
    }

    @Override
    @LuaFunction
    public LuaJavaObject GetContainedObject()
    {
        return new LuaJavaObject(plantable);
    }
}
