package mrthomas20121.tfcflux_machine.objects.tiles.steam_machines;

import mrthomas20121.tfcflux_machine.objects.tiles.TeFluid;
import net.minecraftforge.fluids.FluidRegistry;

public class TESteamCrusher extends TeFluid {
    public TESteamCrusher()
    {
        super(3, 7000, FluidRegistry.getFluid("steam"), 0, 0);
    }
}
