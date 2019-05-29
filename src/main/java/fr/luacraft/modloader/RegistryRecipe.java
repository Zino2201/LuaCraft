package fr.luacraft.modloader;

import java.util.HashMap;

public class RegistryRecipe
{
    private boolean shapeless;
    private String output;
    private Object[] params;
    private HashMap<String, Character> idCharMap;

    public RegistryRecipe(boolean shapeless, String output, Object[] params, HashMap<String, Character> idCharMap)
    {
        this.shapeless = shapeless;
        this.output = output;
        this.params = params;
        this.idCharMap = idCharMap;
    }

    public boolean isShapeless()
    {
        return shapeless;
    }

    public String getOutput()
    {
        return output;
    }

    public Object[] getParams()
    {
        return params;
    }

    public HashMap<String, Character> getIdCharMap()
    {
        return idCharMap;
    }
}
