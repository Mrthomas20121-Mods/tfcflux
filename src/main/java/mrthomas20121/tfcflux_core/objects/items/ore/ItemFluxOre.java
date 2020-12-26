package mrthomas20121.tfcflux_core.objects.items.ore;

import net.dries007.tfc.api.capability.size.Size;
import net.dries007.tfc.api.capability.size.Weight;
import net.dries007.tfc.api.types.Ore;
import net.dries007.tfc.objects.items.ItemTFC;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class ItemFluxOre extends ItemTFC {

    public ItemFluxOre(Ore ore, ItemType type) {

    }

    @Nonnull
    @Override
    public Size getSize(@Nonnull ItemStack itemStack) {
        return Size.SMALL;
    }

    @Nonnull
    @Override
    public Weight getWeight(@Nonnull ItemStack itemStack) {
        return Weight.LIGHT;
    }

    public enum ItemType {
        DUST,
        PLATE,
    }
}
