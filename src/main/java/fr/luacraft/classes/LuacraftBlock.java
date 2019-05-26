package fr.luacraft.classes;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

/**
 * A Luacraft block compatible with Luacraft functions
 */
public class LuacraftBlock extends Block
{
    public LuacraftBlock(String name)
    {
        super(Material.wood);

        this.setBlockName(name);
    }
}
