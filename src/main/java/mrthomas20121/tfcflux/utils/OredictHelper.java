package mrthomas20121.tfcflux.utils;

import mrthomas20121.tfcflux.api.types.ItemMetalType;
import mrthomas20121.tfcflux.objects.items.metal.ItemFluxMetal;
import net.dries007.tfc.api.types.Metal;
import net.dries007.tfc.objects.items.ItemTFC;
import net.minecraftforge.oredict.OreDictionary;

public class OredictHelper {
    public static void registerMetal(String metalName, ItemFluxMetal itemFluxMetal)
    {
        Metal metal = itemFluxMetal.getMetal();
        ItemMetalType type = itemFluxMetal.getType();
        switch (type) {
        case DOUBLE_PLATE:
            registerOre(itemFluxMetal, "plateDouble"+metalName);
            break;
        case PLATE:
            registerOre(itemFluxMetal, "plate"+metalName);
            break;
        case SMALL_DUST:
            registerOre(itemFluxMetal, "dustSmall"+metalName);
            break;
        }
    }
    public static void registerOre(ItemTFC itemTFC, String name)
    {
        OreDictionary.registerOre(name, itemTFC);
    }
}
