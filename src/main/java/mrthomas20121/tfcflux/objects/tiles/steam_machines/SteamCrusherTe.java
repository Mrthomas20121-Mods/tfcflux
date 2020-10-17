package mrthomas20121.tfcflux.objects.tiles.steam_machines;

import mrthomas20121.tfcflux.objects.tiles.TeFluid;
import net.minecraftforge.fluids.FluidRegistry;

public class SteamCrusherTe extends TeFluid {
    public SteamCrusherTe()
    {
        super(2, 7000, FluidRegistry.getFluid("steam"), 0, 0);
    }
}
