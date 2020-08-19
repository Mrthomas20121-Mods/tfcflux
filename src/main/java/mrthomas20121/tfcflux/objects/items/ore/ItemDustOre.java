package mrthomas20121.tfcflux.objects.items.ore;

import net.dries007.tfc.api.capability.size.Size;
import net.dries007.tfc.api.capability.size.Weight;
import net.dries007.tfc.api.types.Ore;
import net.dries007.tfc.objects.items.ItemTFC;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentTranslation;

import javax.annotation.Nonnull;

public class ItemDustOre extends ItemTFC {

    private Ore ore;

    public ItemDustOre(Ore ore)
    {
        this.ore = ore;
    }

    @Nonnull
    @Override
    public Size getSize(ItemStack stack)
    {
        return Size.VERY_SMALL;
    }

    @Nonnull
    @Override
    public Weight getWeight(ItemStack stack)
    {
        return Weight.LIGHT;
    }

    @Override
    @Nonnull
    public String getItemStackDisplayName(@Nonnull ItemStack stack)
    {
        String name = (new TextComponentTranslation("tfc.types.ore."+ore.getRegistryName().getPath().toLowerCase())).getFormattedText();
        return (new TextComponentTranslation("item.tfcflux.ore.dust.name", name)).getFormattedText();
    }
}
