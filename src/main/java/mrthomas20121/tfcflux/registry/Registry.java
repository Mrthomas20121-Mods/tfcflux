package mrthomas20121.tfcflux.registry;

import mrthomas20121.tfcflux.TfcFlux;
import mrthomas20121.tfcflux.api.type.CrushedType;
import mrthomas20121.tfcflux.api.type.Dust;
import mrthomas20121.tfcflux.api.type.ItemMetalType;
import mrthomas20121.tfcflux.objects.blocks.machine.BlockSteamCrusher;
import mrthomas20121.tfcflux.objects.items.block.ItemBlockFlux;
import mrthomas20121.tfcflux.objects.blocks.machine.BlockMachine;
import mrthomas20121.tfcflux.objects.items.dust.ItemDust;
import mrthomas20121.tfcflux.objects.items.ore.ItemDustOre;
import mrthomas20121.tfcflux.objects.tiles.machines.CrusherTe;
import mrthomas20121.tfcflux.objects.tiles.TeEnergy;
import mrthomas20121.tfcflux.objects.items.metal.*;
import mrthomas20121.tfcflux.objects.tiles.TeFluid;
import mrthomas20121.tfcflux.objects.tiles.steam_machines.SteamCrusherTe;
import mrthomas20121.tfcflux.utils.OredictHelper;
import net.dries007.tfc.api.recipes.WeldingRecipe;
import net.dries007.tfc.api.recipes.anvil.AnvilRecipe;
import net.dries007.tfc.api.registries.TFCRegistries;
import net.dries007.tfc.api.types.Metal;
import net.dries007.tfc.api.types.Ore;
import net.dries007.tfc.objects.inventory.ingredient.IIngredient;
import net.dries007.tfc.objects.items.ItemTFC;
import net.dries007.tfc.objects.items.metal.ItemMetal;
import net.dries007.tfc.objects.recipes.ShapelessDamageRecipe;
import net.dries007.tfc.util.forge.ForgeRule;
import net.dries007.tfc.util.skills.SmithingSkill;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.OreIngredient;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryModifiable;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;

import static mrthomas20121.tfcflux.TfcFlux.MODID;
import static mrthomas20121.tfcflux.objects.CreativeTabsTFCFlux.*;
import static net.dries007.tfc.api.types.Metal.ItemType.BUCKET;

@Mod.EventBusSubscriber(modid = MODID)
public class Registry
{
    public static ArrayList<ItemTFC> simpleItems = new ArrayList<>();
    public static ArrayList<ItemFluxMetal> metalItems = new ArrayList<>();
    public static ArrayList<Block> blocks = new ArrayList<>();

    public static BlockMachine crusher = register("machine/crusher", new BlockMachine(Material.ROCK), CT_Machines);
    public static BlockSteamCrusher steamCrusher = register("machine/steam_crusher", new BlockSteamCrusher(Material.ROCK), CT_Machines);

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
                r.register(new ShapelessDamageRecipe(new ResourceLocation("tfc:damage_item_shapeless"), ingredients, new ItemStack(ItemMetal.get(ore.getMetal(), Metal.ItemType.DUST), 1), 2).setRegistryName(new ResourceLocation(TfcFlux.MODID, "crushed/"+ore.getRegistryName().getPath())));
            }
        }
    }

    @SuppressWarnings("unchecked")
    @SubscribeEvent
    public static void onRegisterAnvilRecipeEvent(RegistryEvent.Register<AnvilRecipe> event)
    {
        IForgeRegistry<AnvilRecipe> r = event.getRegistry();

        IForgeRegistryModifiable<AnvilRecipe> mod = (IForgeRegistryModifiable)r;
        mod.remove(new ResourceLocation("tfc:red_steel_bucket"));
        mod.remove(new ResourceLocation("tfc:blue_steel_bucket"));

        r.register(getGeneralAnvilRecipe(new ResourceLocation(TfcFlux.MODID, "red_steel_bucket"), IIngredient.of(ItemFluxMetal.get(Metal.RED_STEEL, ItemMetalType.DOUBLE_PLATE, 1)), new ItemStack(ItemMetal.get(Metal.RED_STEEL, BUCKET)), Metal.Tier.TIER_VI, ForgeRule.BEND_LAST, ForgeRule.BEND_SECOND_LAST, ForgeRule.BEND_THIRD_LAST));
        r.register(getGeneralAnvilRecipe(new ResourceLocation(TfcFlux.MODID, "blue_steel_bucket"), IIngredient.of(ItemFluxMetal.get(Metal.BLUE_STEEL, ItemMetalType.DOUBLE_PLATE, 1)), new ItemStack(ItemMetal.get(Metal.BLUE_STEEL, BUCKET)), Metal.Tier.TIER_VI, ForgeRule.BEND_LAST, ForgeRule.BEND_SECOND_LAST, ForgeRule.BEND_THIRD_LAST));

        for(Metal metal : TFCRegistries.METALS.getValuesCollection())
        {
            if(metal != Metal.UNKNOWN)
            {
                String name = metal.getRegistryName().getPath();
                r.register(getGeneralAnvilRecipe(metal, new ResourceLocation(TfcFlux.MODID, "anvil_plate_"+name), IIngredient.of("ingot"+cap(name)),ItemFluxMetal.get(metal, ItemMetalType.PLATE, 1), ForgeRule.HIT_LAST, ForgeRule.BEND_SECOND_LAST));
            }
        }
    }

    @SubscribeEvent
    public static void onRegisterWeldingRecipeEvent(RegistryEvent.Register<WeldingRecipe> event)
    {
        IForgeRegistry<WeldingRecipe> r = event.getRegistry();
        for(Metal metal : TFCRegistries.METALS.getValuesCollection())
        {
            if(metal != Metal.UNKNOWN)
            {
                String name = metal.getRegistryName().getPath();
                IIngredient<ItemStack> stackIIngredient = IIngredient.of("plate"+cap(name));

                r.register(new WeldingRecipe(new ResourceLocation(TfcFlux.MODID, "double_plate_"+name), stackIIngredient, stackIIngredient, ItemFluxMetal.get(metal, ItemMetalType.DOUBLE_PLATE, 1), metal.getTier()));
            }
        }

    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event)
    {
        IForgeRegistry<Item> r = event.getRegistry();
        for (Metal metal : TFCRegistries.METALS.getValuesCollection())
        {
            String metalName = getMetalName(metal);

            if(metal != Metal.UNKNOWN)
            {
                // small dust
                ItemFluxMetal small_dust = registerItemMetal(r, "metal/small_dust/" + metalName, new ItemFluxMetal(metal, ItemMetalType.SMALL_DUST), CT_Metals);
                // plate
                ItemFluxMetal plate = registerItemMetal(r, "metal/plate/" + metalName, new ItemFluxMetal(metal, ItemMetalType.PLATE), CT_Metals);
                // double plate
                ItemFluxMetal double_plate = registerItemMetal(r, "metal/double_plate/" + metalName, new ItemFluxMetal(metal, ItemMetalType.DOUBLE_PLATE), CT_Metals);

            }
        }
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
        r.register(crusher);
        r.register(steamCrusher);

        // te
        registerTe(CrusherTe.class, crusher.getLocalizedName());
        registerTe(SteamCrusherTe.class, steamCrusher.getLocalizedName());
        registerTe(TeEnergy.class, "teEnergy");
        registerTe(TeFluid.class, "teFluid");
    }

    private static String cap(String str)
    {
        String[] array = str.split("_");
        StringBuilder s = new StringBuilder();
        for(String string: array) {
            s.append(StringUtils.capitalize(string));
        }
        return s.toString();
    }

    private static AnvilRecipe getGeneralAnvilRecipe(Metal metal, ResourceLocation location, IIngredient<ItemStack> ingredient, ItemStack output, ForgeRule... rules)
    {
        return new AnvilRecipe(location, ingredient, output, metal.getTier(), SmithingSkill.Type.GENERAL, rules);
    }
    private static AnvilRecipe getGeneralAnvilRecipe(ResourceLocation location, IIngredient<ItemStack> ingredient, ItemStack output, Metal.Tier tier,ForgeRule... rules)
    {
        return new AnvilRecipe(location, ingredient, output, tier, SmithingSkill.Type.GENERAL, rules);
    }

    private static String getMetalName(Metal metal)
    {
        return metal.getRegistryName().getPath();
    }
    private static String getOreName(Ore ore)
    {
        return ore.getRegistryName().getPath();
    }


    private static ItemFluxMetal registerItemMetal(IForgeRegistry<Item> r, String name, ItemFluxMetal item, CreativeTabs ct)
    {
        ItemFluxMetal itemTFC = register(r, name, item, ct);
        OredictHelper.registerMetal(cap(itemTFC.getMetal().getRegistryName().getPath()), item);
        metalItems.add(itemTFC);
        return itemTFC;
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
