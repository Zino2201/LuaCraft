package fr.luacraft.core.api.libs;

import com.naef.jnlua.JavaFunction;
import com.naef.jnlua.LuaState;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import fr.luacraft.core.Luacraft;
import fr.luacraft.core.api.ILuaContainer;
import fr.luacraft.core.api.creativetab.LuaCreativeTab;
import fr.luacraft.core.api.blocks.LuaBlock;
import fr.luacraft.core.api.items.LuaItem;
import fr.luacraft.core.api.nbt.LuaNBTTagCompound;
import fr.luacraft.core.api.registry.LuaGameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.oredict.OreDictionary;

/**
 * Base minecraft library
 * mc.*
 * @author Zino
 */
@LuaLibrary
public class MinecraftLib
{
    /**
     * Register a recipe
     */
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

    /**
     * Register a new smelting
     */
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

    /**
     * Create a NBTTagCompound object
     */
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

    /**
     * Add a ore to the ore dictionary
     */
    public static JavaFunction AddOreDictionaryKey = new JavaFunction()
    {
        @Override
        public int invoke(LuaState l)
        {
            String name = l.checkString(1);
            ILuaContainer object = l.checkJavaObject(2, ILuaContainer.class);
            if(object instanceof LuaBlock)
                OreDictionary.registerOre(name, ((LuaBlock) (object)).getBlock());
            else if(object instanceof LuaItem)
                OreDictionary.registerOre(name, ((LuaItem) (object)).getItem());

            return 0;
        }
    };

    /**
     * Get a block from its ID
     */
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
     * Get a item from a block
     */
    public static JavaFunction GetItemFromBlock = new JavaFunction()
    {
        @Override
        public int invoke(LuaState l)
        {
            String id = l.checkString(1);
            LuaItem output = null;

            if(id.contains(""))
            {
                String[] formattedID = id.split(":");

                output = new LuaItem(Item.getItemFromBlock(GameRegistry.findBlock(formattedID[0], formattedID[1])));
            }
            else
            {
                output = new LuaItem(Item.getItemFromBlock(GameRegistry.findBlock("minecraft", id)));
            }

            l.pushJavaObject(output);

            return 1;
        }
    };

    /**
     * Get creative tab from name
     */
    public static JavaFunction GetCreativeTabFromName = new JavaFunction()
    {
        @Override
        public int invoke(LuaState l)
        {
            String name = l.checkString(1);
            LuaCreativeTab output = null;

            if(Luacraft.getInstance().getProxy().getSide() == Side.CLIENT)
            {
                for (CreativeTabs tab : CreativeTabs.creativeTabArray)
                {
                    if (tab.getTabLabel().equals(name))
                    {
                        output = new LuaCreativeTab(tab);
                        break;
                    }
                }
            }

            l.pushJavaObject(output);

            return 1;
        }
    };

    /**
     * Add a language key to LanguageRegistry
     */
    @SuppressWarnings("deprecated")
    public static JavaFunction AddLangKey = new JavaFunction()
    {
        @Override
        public int invoke(LuaState l)
        {
            String lang = l.checkString(1);
            String key = l.checkString(2);
            String value = l.checkString(3);

            LanguageRegistry.instance().addStringLocalization(key, lang, value);

            return 0;
        }
    };

    /**
     * initialize the library
     * @param l
     */
    public static void initialize(LuaState l)
    {
        /** Global scope */
        l.pushJavaObject(NBTTagCompound);
        l.setGlobal("NBTTagCompound");

        /** Minecraft table */
        l.newTable();
        l.pushJavaObject(AddRecipe);
        l.setField(-2, "AddRecipe");
        l.pushJavaObject(AddSmelting);
        l.setField(-2, "AddSmelting");
        l.pushJavaObject(AddOreDictionaryKey);
        l.setField(-2, "AddOreDictionaryKey");
        l.pushJavaObject(GetBlockByID);
        l.setField(-2, "GetBlockByID");
        l.pushJavaObject(GetItemFromBlock);
        l.setField(-2, "GetItemFromBlock");
        l.pushJavaObject(AddLangKey);
        l.setField(-2, "AddLangKey");
        l.pushJavaObject(GetCreativeTabFromName);
        l.setField(-2, "GetCreativeTabFromName");
        l.setGlobal("mc");
    }
}
