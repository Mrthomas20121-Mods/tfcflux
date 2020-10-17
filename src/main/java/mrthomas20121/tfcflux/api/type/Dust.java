package mrthomas20121.tfcflux.api.type;

import mrthomas20121.rocksalt.utils.FluidUtils;
import net.dries007.tfc.objects.fluids.properties.FluidWrapper;

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

    public FluidWrapper generateFluid()
    {
        return FluidUtils.registerLiquid(this.name().toLowerCase(),this.color);
    }
}
