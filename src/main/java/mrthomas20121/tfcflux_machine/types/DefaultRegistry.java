package mrthomas20121.tfcflux_machine.types;

import mrthomas20121.tfcflux_machine.TfcFluxMachine;
import mrthomas20121.tfcflux_machine.api.type.CrushedType;
import mrthomas20121.tfcflux_machine.api.type.Dust;
import mrthomas20121.tfcflux_machine.api.type.Machine;
import mrthomas20121.tfcflux_machine.objects.items.block.ItemBlockFlux;
import mrthomas20121.tfcflux_machine.objects.blocks.BlockMachine;
import mrthomas20121.tfcflux_machine.objects.items.dust.ItemDust;
import mrthomas20121.tfcflux_machine.objects.items.ore.ItemDustOre;
import mrthomas20121.tfcflux_machine.objects.tiles.machines.TECrusher;
import mrthomas20121.tfcflux_machine.objects.tiles.TeEnergy;
import mrthomas20121.tfcflux_core.objects.items.metal.*;
import mrthomas20121.tfcflux_machine.objects.tiles.TeFluid;
import mrthomas20121.tfcflux_machine.objects.tiles.machines.TEElectricFurnace;
import mrthomas20121.tfcflux_machine.objects.tiles.steam_machines.TESteamCrusher;
import net.dries007.tfc.api.registries.TFCRegistries;
import net.dries007.tfc.api.types.Metal;
import net.dries007.tfc.api.types.Ore;
import net.dries007.tfc.objects.items.ItemTFC;
import net.dries007.tfc.objects.items.metal.ItemMetal;
import net.dries007.tfc.objects.recipes.ShapelessDamageRecipe;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.OreIngredient;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;

import static mrthomas20121.tfcflux_machine.TfcFluxMachine.MODID;
import static mrthomas20121.tfcflux_core.objects.CreativeTabsTFCFlux.*;

public class DefaultRegistry
{
    public static ArrayList<ItemTFC> simpleItems = new ArrayList<>();
    public static ArrayList<ItemFluxMetal> metalItems = new ArrayList<>();
    public static ArrayList<Block> blocks = new ArrayList<>();

    public static void preInit()
    {
    }

    @SubscribeEvent
    public static void onRegisterRecipesEvent(RegistryEvent.Register<IRecipe> event)
    {
        IForgeRegistry<IRecipe> r = event.getRegistry();

        for(Ore ore : TFCRegistries.ORES.getValuesCollection())
        {
            NonNullList<Ingredient> ingredients = NonNullList.create();

            if(ore.getMetal() != null)
            {
                ingredients.add(new OreIngredient("hammer"));
                ingredients.add(Ingredient.fromItem(ItemOreCrushed.get(ore, CrushedType.PULVERIZED)));
                r.register(new ShapelessDamageRecipe(new ResourceLocation("tfc:damage_item_shapeless"), ingredients, new ItemStack(ItemMetal.get(ore.getMetal(), Metal.ItemType.DUST), 1), 2).setRegistryName(new ResourceLocation(TfcFluxMachine.MODID, "crushed/"+ore.getRegistryName().getPath())));
            }
        }
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event)
    {
        IForgeRegistry<Item> r = event.getRegistry();
        for (Ore ore : TFCRegistries.ORES.getValuesCollection())
        {
            String oreName = getOreName(ore);
            Metal metal = ore.getMetal();
            if(metal != null) {
                String metalName = getMetalName(metal);

                ItemTFC rich_crushed = register(r, "ore/pulverized/rich/" + oreName, new ItemOreCrushed(ore, CrushedType.PULVERIZED_RICH), CT_Metals, "crushedRich");
                ItemTFC poor_crushed = register(r, "ore/pulverized/poor/" + oreName, new ItemOreCrushed(ore, CrushedType.PULVERIZED_POOR), CT_Metals, "crushedPoor");

                ItemTFC small_crushed = register(r, "ore/pulverized/small/" + oreName, new ItemOreCrushed(ore, CrushedType.PULVERIZED_SMALL), CT_Metals, "crushedSmall");

                ItemTFC purified_rich = register(r, "ore/purified/rich/" + oreName, new ItemOreCrushed(ore, CrushedType.PURIFIED_RICH), CT_Metals, "purifiedRich");
                ItemTFC purified_poor = register(r, "ore/purified/poor/" + oreName, new ItemOreCrushed(ore, CrushedType.PURIFIED_POOR), CT_Metals, "purifiedPoor");
                ItemTFC purified_small = register(r, "ore/purified/small/" + oreName, new ItemOreCrushed(ore, CrushedType.PURIFIED_SMALL), CT_Metals, "purifiedSmall");

                // crushed ore
                ItemTFC normal_crushed = register(r, "ore/pulverized/normal/" + oreName, new ItemOreCrushed(ore, CrushedType.PULVERIZED), CT_Metals, "crushedNormal");

                // purified ore
                ItemTFC purified_item = register(r, "ore/purified/normal/" + oreName, new ItemOreCrushed(ore, CrushedType.PURIFIED), CT_Metals, "purifiedNormal");
            }
            else {
                if(!oreName.equals("graphite") && !oreName.equals("kaolinite") && !oreName.equals("sulfur"))
                {
                    ItemTFC dust = register(r, "powder/"+oreName, new ItemDustOre(ore), CT_Metals, "dust"+cap(oreName));
                }
            }
        }
        for(Dust dust : Dust.values())
        {
            registerDust(r, dust);
        }

        for(Block block: blocks)
        {
            ItemBlockFlux item = new ItemBlockFlux(block, block.getRegistryName());
            item.setCreativeTab(block.getCreativeTab());
            r.register(item);
        }
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event)
    {
        IForgeRegistry<Block> r = event.getRegistry();
        // machines
        for (Machine machine : Machine.values())
        {
            registerMachine(r, machine);
        }

        // te
        registerTe(TECrusher.class, "te_crusher");
        registerTe(TESteamCrusher.class, "te_steam_crusher");
        registerTe(TeEnergy.class, "teEnergy");
        registerTe(TeFluid.class, "teFluid");
        registerTe(TEElectricFurnace.class, "te_electric_furnace");
    }

    public static String cap(String str)
    {
        String[] array = str.split("_");
        StringBuilder s = new StringBuilder();
        for(String string: array) {
            s.append(StringUtils.capitalize(string));
        }
        return s.toString();
    }


    private static String getMetalName(Metal metal)
    {
        return metal.getRegistryName().getPath();
    }
    private static String getOreName(Ore ore)
    {
        return ore.getRegistryName().getPath();
    }


    private static void registerMachine(IForgeRegistry<Block> r, Machine machine)
    {
        BlockMachine blockMachine = BlockMachine.create(machine);
        r.register(blockMachine);
        blocks.add(blockMachine);
    }

    private static ItemDust registerDust(IForgeRegistry<Item> r, Dust dust)
    {
        ItemDust itemDust = new ItemDust(dust);
        r.register(itemDust);
        return itemDust;
    }

    private static ItemTFC register(IForgeRegistry<Item> r, String name, ItemTFC item, CreativeTabs ct, String oredict)
    {
        ItemTFC itemTFC = register(r, name, item, ct);
        OreDictionary.registerOre(oredict, item);
        return itemTFC;
    }
    private static <T extends ItemTFC> T register(IForgeRegistry<Item> r, String name, T item, CreativeTabs ct)
    {
        item.setRegistryName(MODID, name);
        item.setTranslationKey(MODID + "." + name.replace('/', '.'));
        item.setCreativeTab(ct);
        r.register(item);
        simpleItems.add(item);
        return item;
    }
    private static <T extends Block> T register(String name, T block, CreativeTabs ct)
    {
        block.setRegistryName(MODID, name);
        block.setTranslationKey(MODID + "." + name.replace('/', '.'));
        block.setCreativeTab(ct);
        blocks.add(block);
        return block;
    }
    private static <T extends TileEntity> void registerTe(Class<T> te, String name)
    {
        TileEntity.register(MODID + ":" + name, te);
    }
}
