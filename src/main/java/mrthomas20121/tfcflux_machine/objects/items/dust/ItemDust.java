package mrthomas20121.tfcflux_machine.objects.items.dust;

import mrthomas20121.tfcflux_machine.TfcFluxMachine;
import mrthomas20121.tfcflux_machine.api.type.Dust;
import net.dries007.tfc.api.capability.size.Size;
import net.dries007.tfc.api.capability.size.Weight;
import net.dries007.tfc.objects.items.ItemTFC;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import java.util.EnumMap;

import static mrthomas20121.tfcflux_core.objects.CreativeTabsTFCFlux.CT_Metals;

public class ItemDust extends ItemTFC {

    private static EnumMap<Dust, ItemDust> map = new EnumMap<>(Dust.class);

    public static ItemDust get(Dust dust)
    {
        return map.get(dust);
    }
    public static ItemStack get(Dust dust, int amount)
    {
        return new ItemStack(get(dust), amount);
    }

    public ItemDust(Dust dust)
    {
        String name = "powder/"+dust.name().toLowerCase();
        this.setRegistryName(TfcFluxMachine.MODID, name);
        this.setTranslationKey(TfcFluxMachine.MODID + "." + name.replace('/', '.'));
        if(!map.containsKey(dust))
        {
            map.put(dust, this);
        }
        this.setCreativeTab(CT_Metals);
    }

    @Nonnull
    @Override
    public Weight getWeight(@Nonnull ItemStack itemStack) {
        return Weight.VERY_LIGHT;
    }

    @Nonnull
    @Override
    public Size getSize(@Nonnull ItemStack itemStack) {
        return Size.VERY_SMALL;
    }
}
