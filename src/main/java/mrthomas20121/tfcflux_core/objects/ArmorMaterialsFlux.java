package mrthomas20121.tfcflux_core.objects;

import net.dries007.tfc.objects.ArmorMaterialTFC;
import net.minecraft.init.SoundEvents;
import net.minecraftforge.common.util.EnumHelper;

import static net.dries007.tfc.TerraFirmaCraft.MOD_ID;

public class ArmorMaterialsFlux {

    public static final ArmorMaterialTFC chromium = new ArmorMaterialTFC(EnumHelper.addArmorMaterial("tfcflux_chromium", MOD_ID + ":chromium", 14, new int[] {1, 3, 4, 1}, 15, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F), 10, 10, 6.25f);
    public static final ArmorMaterialTFC aluminium_steel = new ArmorMaterialTFC(EnumHelper.addArmorMaterial("tfcflux_aluminium_steel", MOD_ID + ":aluminium_steel", 68, new int[] {3, 6, 8, 3}, 23, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 2.0F), 50, 62.5f, 50);
    public static final ArmorMaterialTFC bismuth_steel = new ArmorMaterialTFC(EnumHelper.addArmorMaterial("tfcflux_bismuth_steel", MOD_ID + ":bismuth_steel", 68, new int[] {3, 6, 8, 3}, 23, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 2.0F), 50, 62.5f, 50);
    public static final ArmorMaterialTFC damascus_steel = new ArmorMaterialTFC(EnumHelper.addArmorMaterial("tfcflux_damascus_steel", MOD_ID + ":damascus_steel", 100, new int[] {3, 6, 8, 3}, 23, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 2.0F), 45, 57.5f, 45);

}
