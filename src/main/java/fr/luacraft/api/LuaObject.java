package fr.luacraft.api;

public class LuaObject
{
    protected String id;

    public LuaObject(String id)
    {
        this.id = id;
    }

    public String getTypeName()
    {
        return "Object";
    }

    public String getID()
    {
        return id;
    }

    public Object getContainedObject()
    {
        return null;
    }
}
