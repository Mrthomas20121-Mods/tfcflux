package mrthomas20121.tfcflux.objects.items.metal;

import mrthomas20121.tfcflux.api.types.CrushedType;
import net.dries007.tfc.api.capability.size.Size;
import net.dries007.tfc.api.capability.size.Weight;
import net.dries007.tfc.api.types.Ore;
import net.dries007.tfc.objects.items.ItemTFC;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ItemOreCrushed extends ItemTFC {

    private static final Map<Ore, EnumMap<CrushedType, ItemOreCrushed>> TABLE = new HashMap<>();
    private Ore ore;
    private CrushedType type;

    public static Item get(Ore ore, CrushedType type)
    {
        return TABLE.get(ore).get(type);
    }

    public static Set<CrushedType> getTypes(Ore ore)
    {
        return TABLE.get(ore).keySet();
    }

    public static boolean hasType(Ore ore, CrushedType type) {
        return getTypes(ore).contains(type);
    }

    public ItemOreCrushed(Ore ore, CrushedType type) {
        this.ore = ore;
        this.type = type;
        setMaxDamage(0);
        if (!TABLE.containsKey(ore))
        {
            TABLE.put(ore, new EnumMap<>(CrushedType.class));
        }
        TABLE.get(ore).put(type, this);
    }

    @Nonnull
    @Override
    public Size getSize(ItemStack stack)
    {
        return Size.SMALL;
    }

    @Nonnull
    @Override
    public Weight getWeight(ItemStack stack)
    {
        return Weight.VERY_LIGHT;
    }

    @Nonnull
    public Ore getOre()
    {
        return ore;
    }
}
