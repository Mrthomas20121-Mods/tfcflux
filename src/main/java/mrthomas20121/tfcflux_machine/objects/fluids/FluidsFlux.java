package mrthomas20121.tfcflux_machine.objects.fluids;

import com.google.common.collect.HashBiMap;
import net.dries007.tfc.objects.fluids.properties.FluidWrapper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

import javax.annotation.Nonnull;

import static net.dries007.tfc.TerraFirmaCraft.MOD_ID;

public class FluidsFlux
{

    private static final ResourceLocation STILL = new ResourceLocation(MOD_ID, "blocks/fluid_still");
    private static final ResourceLocation FLOW = new ResourceLocation(MOD_ID, "blocks/fluid_flow");

    private static final ResourceLocation LAVA_STILL = new ResourceLocation(MOD_ID, "blocks/lava_still");
    private static final ResourceLocation LAVA_FLOW = new ResourceLocation(MOD_ID, "blocks/lava_flow");

    private static final HashBiMap<Fluid, FluidWrapper> WRAPPERS = HashBiMap.create();

    public static FluidWrapper MUD;

    public static void registerFluids()
    {
        MUD = registerFluid(new Fluid("mud", STILL, FLOW, 0x5B4C3E));
    }
    private static FluidWrapper registerFluid(@Nonnull Fluid newFluid)
    {
        boolean isDefault = FluidRegistry.registerFluid(newFluid);
        if (!isDefault)
        {
            // Fluid was already registered with this name, default to that fluid
            newFluid = FluidRegistry.getFluid(newFluid.getName());
        }
        FluidRegistry.addBucketForFluid(newFluid);
        FluidWrapper properties = new FluidWrapper(newFluid, isDefault);
        WRAPPERS.put(newFluid, properties);
        return properties;
    }

}
