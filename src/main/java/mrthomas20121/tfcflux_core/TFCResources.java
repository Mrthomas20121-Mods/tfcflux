package mrthomas20121.tfcflux_core;

import mrthomas20121.rocksalt.utils.TFCUtils;
import net.dries007.tfc.TerraFirmaCraft;
import net.dries007.tfc.api.recipes.AlloyRecipe;
import net.dries007.tfc.api.registries.TFCRegistryEvent;
import net.dries007.tfc.api.types.Metal;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(modid = TFCFluxCore.MOD_ID)
public class TFCResources {

    // metals
    public static ResourceLocation chromium = new ResourceLocation(TerraFirmaCraft.MOD_ID,"chromium");
    public static ResourceLocation kanthal = new ResourceLocation(TerraFirmaCraft.MOD_ID,"kanthal"); // 30-45% iron +  30-45% chromium + 5-10% aluminium
    public static ResourceLocation solder = new ResourceLocation(TerraFirmaCraft.MOD_ID,"solder");
    public static ResourceLocation aluminium_steel = new ResourceLocation(TerraFirmaCraft.MOD_ID,"aluminium_steel");
    public static ResourceLocation weak_aluminium_steel = new ResourceLocation(TerraFirmaCraft.MOD_ID,"weak_aluminium_steel");
    public static ResourceLocation bismuth_steel = new ResourceLocation(TerraFirmaCraft.MOD_ID,"bismuth_steel");
    public static ResourceLocation damascus_steel = new ResourceLocation(TerraFirmaCraft.MOD_ID,"damascus_steel"); // 50% Blue steel + 5-10% pig iron + 40% rose gold
    public static ResourceLocation stainless_steel = new ResourceLocation(TerraFirmaCraft.MOD_ID,"stainless_steel"); // 50% ferrochrome + 30% manganese + 10% aluminium + 10% titanium
    public static ResourceLocation rose_alloy = new ResourceLocation(TerraFirmaCraft.MOD_ID,"rose_alloy"); // 50% bismuth bronze + 25-28% lead + 22-25% tin. used to make rails, pipes and stuff.
    public static ResourceLocation ferrochrome = new ResourceLocation(TerraFirmaCraft.MOD_ID,"ferrochrome"); // 50-70% chromium + 30% iron
    public static ResourceLocation cadmium = new ResourceLocation(TerraFirmaCraft.MOD_ID, "cadmium");
    public static ResourceLocation nichrome = new ResourceLocation(TerraFirmaCraft.MOD_ID, "nichrome");

    @SubscribeEvent
    public static void registerMetals(TFCRegistryEvent.RegisterPreBlock<Metal> event) {
        IForgeRegistry<Metal> r = event.getRegistry();
        r.registerAll(
                new Metal(chromium, Metal.Tier.TIER_III, true, 0.5f, 1900f, 0xAF7CAA, null, null),
                new Metal(kanthal, Metal.Tier.TIER_III, true, 0.35f, 1500f, 0xAD9CAB, null, null),
                new Metal(solder, Metal.Tier.TIER_VI, true, 0.35f, 2000f, 0xA19A99, null, null),
                new Metal(aluminium_steel, Metal.Tier.TIER_VI, true, 0.35f, 1540f, 0x8FA5A6, null, null),
                new Metal(weak_aluminium_steel, Metal.Tier.TIER_VI, false, 0.35f, 1540f, 0x8FA5A6, null, null),
                new Metal(ferrochrome, Metal.Tier.TIER_VI, true, 0.35f, 2800f, 0x4A4A64, null, null),
                new Metal(cadmium, Metal.Tier.TIER_VI, true, 0.35f, 1000f, 0x5C5C5E, null, null),
                new Metal(nichrome, Metal.Tier.TIER_VI, true, 0.35f, 1400f, 0x77898E, null, null));
    }

    @SubscribeEvent
    public static void registerAlloys(RegistryEvent.Register<AlloyRecipe> event) {
        IForgeRegistry<AlloyRecipe> r = event.getRegistry();

    }


}
