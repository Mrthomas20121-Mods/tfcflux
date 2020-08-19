package mrthomas20121.tfcflux.objects.tiles;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;

import javax.annotation.Nullable;

public class FluidTankFlux extends FluidTank {
    public FluidTankFlux(int capacity)
    {
        super(capacity);
    }
    public FluidTankFlux(@Nullable FluidStack fluidStack, int capacity)
    {
        super(fluidStack, capacity);
    }

    public FluidTankFlux(Fluid fluid, int amount, int capacity)
    {
        super(new FluidStack(fluid, amount), capacity);
    }
}
