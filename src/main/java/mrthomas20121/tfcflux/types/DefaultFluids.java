package mrthomas20121.tfcflux.types;

import mrthomas20121.rocksalt.utils.FluidUtils;
import mrthomas20121.tfcflux.api.type.Dust;
import net.dries007.tfc.objects.fluids.properties.FluidWrapper;
import net.dries007.tfc.util.Helpers;

public class DefaultFluids {
    public static FluidWrapper steam = Helpers.getNull();

    public static void preInit()
    {
        steam = FluidUtils.registerLiquid("steam", 0xD0DDD2);

        for(Dust dust : Dust.values())
        {
            dust.generateFluid();
        }
    }
}
