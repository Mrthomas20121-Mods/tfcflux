package mrthomas20121.tfcflux_machine.types;

import mrthomas20121.rocksalt.utils.AnvilUtils;
import mrthomas20121.tfcflux_machine.TfcFluxMachine;
import mrthomas20121.tfcflux_core.objects.items.metal.ItemFluxMetal;
import net.dries007.tfc.api.recipes.WeldingRecipe;
import net.dries007.tfc.api.recipes.anvil.AnvilRecipe;
import net.dries007.tfc.api.registries.TFCRegistries;
import net.dries007.tfc.api.types.Metal;
import net.dries007.tfc.objects.inventory.ingredient.IIngredient;
import net.dries007.tfc.objects.items.metal.ItemMetal;
import net.dries007.tfc.util.forge.ForgeRule;
import net.dries007.tfc.util.skills.SmithingSkill;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryModifiable;

import static net.dries007.tfc.api.types.Metal.ItemType.BUCKET;

public class DefaultRecipes {

    @SuppressWarnings("unchecked")
    @SubscribeEvent
    public static void onRegisterAnvilRecipeEvent(RegistryEvent.Register<AnvilRecipe> event)
    {
        IForgeRegistry<AnvilRecipe> r = event.getRegistry();

        IForgeRegistryModifiable<AnvilRecipe> mod = (IForgeRegistryModifiable)r;
        mod.remove(new ResourceLocation("tfc:red_steel_bucket"));
        mod.remove(new ResourceLocation("tfc:blue_steel_bucket"));

        //r.register(AnvilUtils.createAnvilRecipe(new ResourceLocation(TfcFluxMachine.MODID, "red_steel_bucket"), IIngredient.of(ItemFluxMetal.get(Metal.RED_STEEL, ItemMetalType.DOUBLE_PLATE, 1)), new ItemStack(ItemMetal.get(Metal.RED_STEEL, BUCKET)), Metal.Tier.TIER_VI, SmithingSkill.Type.GENERAL, ForgeRule.BEND_LAST, ForgeRule.BEND_SECOND_LAST, ForgeRule.BEND_THIRD_LAST));
        //r.register(AnvilUtils.createAnvilRecipe(new ResourceLocation(TfcFluxMachine.MODID, "blue_steel_bucket"), IIngredient.of(ItemFluxMetal.get(Metal.BLUE_STEEL, ItemMetalType.DOUBLE_PLATE, 1)), new ItemStack(ItemMetal.get(Metal.BLUE_STEEL, BUCKET)), Metal.Tier.TIER_VI, SmithingSkill.Type.GENERAL, ForgeRule.BEND_LAST, ForgeRule.BEND_SECOND_LAST, ForgeRule.BEND_THIRD_LAST));

        for(Metal metal : TFCRegistries.METALS.getValuesCollection())
        {
            if(metal != Metal.UNKNOWN)
            {
                String name = metal.getRegistryName().getPath().toLowerCase();
                //r.register(AnvilUtils.createAnvilRecipe(new ResourceLocation(TfcFluxMachine.MODID, "anvil_plate_"+name), IIngredient.of("ingot"+DefaultRegistry.cap(name)),ItemFluxMetal.get(metal, ItemMetalType.PLATE, 1), metal.getTier().previous(), SmithingSkill.Type.GENERAL, ForgeRule.HIT_LAST, ForgeRule.BEND_SECOND_LAST));
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
                IIngredient<ItemStack> stackIIngredient = IIngredient.of("plate"+DefaultRegistry.cap(name));

                //r.register(new WeldingRecipe(new ResourceLocation(TfcFluxMachine.MODID, "double_plate_"+name), stackIIngredient, stackIIngredient, ItemFluxMetal.get(metal, ItemMetalType.DOUBLE_PLATE, 1), metal.getTier()));
            }
        }

    }
}
