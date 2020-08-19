package mrthomas20121.tfcflux.objects.tiles;

import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public class SteamCrusherTe extends TeFluid {
    public SteamCrusherTe()
    {
        super(7000, new FluidStack(FluidRegistry.getFluid("steam"), 1000));
    }
}
