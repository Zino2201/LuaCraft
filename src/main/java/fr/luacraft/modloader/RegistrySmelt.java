package fr.luacraft.modloader;

public class RegistrySmelt
{
    private String input;
    private String output;
    private float experienceGain;

    public RegistrySmelt(String input, String output, float experienceGain)
    {
        this.input = input;
        this.output = output;
        this.experienceGain = experienceGain;
    }

    public String getInput()
    {
        return input;
    }

    public String getOutput()
    {
        return output;
    }

    public float getExperienceGain()
    {
        return experienceGain;
    }
}
