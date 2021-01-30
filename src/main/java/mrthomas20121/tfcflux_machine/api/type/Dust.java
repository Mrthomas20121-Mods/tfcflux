package mrthomas20121.tfcflux_machine.api.type;

import net.dries007.tfc.api.capability.food.FoodStatsTFC;
import net.dries007.tfc.objects.fluids.FluidsTFC;
import net.dries007.tfc.objects.fluids.properties.DrinkableProperty;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public enum Dust {
    ROSE_SALT(0xD27A85,true),
    BLACK_SALT(0x080506, true),
    RED_SALT(0xE31F23, true);

    private boolean fluid;
    private int color;
    Dust(int color, boolean fluid)
    {
        this.color = color;
        this.fluid = fluid;
    }

    public int getColor() {
        return color;
    }

    public boolean isFluid() {
        return fluid;
    }

    public Fluid generateFluid()
    {
        final ResourceLocation STILL = new ResourceLocation("tfc", "blocks/fluid_still");
        final ResourceLocation FLOW = new ResourceLocation("tfc", "blocks/fluid_flow");
        Fluid fluid = new Fluid(this.name().toLowerCase(), STILL, FLOW, this.color);
        FluidRegistry.registerFluid(fluid);
        FluidRegistry.addBucketForFluid(fluid);
        FluidsTFC.getWrapper(fluid).with(DrinkableProperty.DRINKABLE, (player) -> {
            if (player.getFoodStats() instanceof FoodStatsTFC) {
                ((FoodStatsTFC)player.getFoodStats()).addThirst(40.0F);
            }
        });
        return fluid;
    }
}
