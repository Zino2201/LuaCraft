package fr.luacraft.api.libs;

import com.naef.jnlua.JavaFunction;
import com.naef.jnlua.LuaState;
import cpw.mods.fml.common.registry.GameRegistry;
import fr.luacraft.api.LuaBlock;
import fr.luacraft.api.LuaItem;
import fr.luacraft.api.LuaNBTTagCompound;
import fr.luacraft.modloader.ILuaObject;
import fr.luacraft.modloader.LuaGameRegistry;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.oredict.OreDictionary;

/**
 * Base minecraft library
 * mc.*
 */
public class MinecraftLib
{
    public static JavaFunction AddRecipe = new JavaFunction()
    {
        @Override
        public int invoke(LuaState l)
        {
            String output = l.checkString(1);
            boolean shapeless = l.toBoolean(2);
            if(!l.isTable(3))
                throw new IllegalArgumentException("mc.AddRecipe arg 3 not a table");

            String[] slots = new String[9];
            l.pushValue(3);
            l.pushNil();
            int i = 0;
            while(l.next(-2))
            {
                //l.pushValue(-2);
                int index = l.checkInteger(-2);
                slots[index - 1] = l.checkString(-1);
                l.pop(1);
            }
            l.pop(1);
            LuaGameRegistry.addRecipe(output, shapeless, slots);
            return 0;
        }
    };

    public static JavaFunction AddSmelting = new JavaFunction()
    {
        @Override
        public int invoke(LuaState l)
        {
            String input = l.checkString(1);
            String output = l.checkString(2);
            float xp = (float) l.checkNumber(3);
            LuaGameRegistry.addSmelting(input, output, xp);
            return 0;
        }
    };

    public static JavaFunction NBTTagCompound = new JavaFunction()
    {
        @Override
        public int invoke(LuaState l)
        {
            LuaNBTTagCompound nbtTagCompound = new LuaNBTTagCompound(new NBTTagCompound());
            l.pushJavaObject(nbtTagCompound);
            return 1;
        }
    };

    public static JavaFunction AddToOreDictionary = new JavaFunction()
    {
        @Override
        public int invoke(LuaState l)
        {
            String name = l.checkString(1);
            ILuaObject object = l.checkJavaObject(2, ILuaObject.class);
            if(object instanceof LuaBlock)
                OreDictionary.registerOre(name, ((LuaBlock) (object)).getBlock());
            else if(object instanceof LuaItem)
                OreDictionary.registerOre(name, ((LuaItem) (object)).getItem());

            return 0;
        }
    };

    public static JavaFunction GetBlockByID = new JavaFunction()
    {
        @Override
        public int invoke(LuaState l)
        {
            String id = l.checkString(1);
            LuaBlock output = null;

            if(id.contains(""))
            {
                String[] formattedID = id.split(":");

                output = new LuaBlock(GameRegistry.findBlock(formattedID[0], formattedID[1]));
            }
            else
            {
                output = new LuaBlock(GameRegistry.findBlock("minecraft", id));
            }

            l.pushJavaObject(output);

            return 1;
        }
    };

    /**
     * Initialize the library
     * @param l
     */
    public static void Initialize(LuaState l)
    {
        /** Global scope */
        l.pushJavaObject(NBTTagCompound);
        l.setGlobal("NBTTagCompound");

        /** Minecraft table */
        l.newTable();
        l.pushJavaObject(AddRecipe);
        l.setField(-2, "addRecipe");
        l.pushJavaObject(AddSmelting);
        l.setField(-2, "addSmelting");
        l.pushJavaObject(AddToOreDictionary);
        l.setField(-2, "addToOreDictionary");
        l.pushJavaObject(GetBlockByID);
        l.setField(-2, "getBlockByID");
        l.setGlobal("mc");
    }
}
