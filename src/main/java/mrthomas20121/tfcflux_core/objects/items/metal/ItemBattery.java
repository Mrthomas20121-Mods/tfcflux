package mrthomas20121.tfcflux_core.objects.items.metal;

import net.dries007.tfc.api.types.Metal;

public class ItemBattery extends ItemFluxMetal {


    public ItemBattery(Metal metal, ItemMetalType type) {
        super(metal, type);
    }

    @Override
    public int getItemEnchantability() {
        return 10;
    }
}
