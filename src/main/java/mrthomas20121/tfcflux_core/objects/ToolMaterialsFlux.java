package mrthomas20121.tfcflux_core.objects;

import net.minecraft.item.Item;
import net.minecraftforge.common.util.EnumHelper;

public class ToolMaterialsFlux {

    public static final Item.ToolMaterial chromium = EnumHelper.addToolMaterial("tfcflux_chromium", 2, 2200, 12, 4.75f, 30);
    public static final Item.ToolMaterial aluminium_steel = EnumHelper.addToolMaterial("tfcflux_aluminium_steel", 3, 6500, 18, 9.0f, 25);
    public static final Item.ToolMaterial bismuth_steel = EnumHelper.addToolMaterial("tfcflux_bismuth_steel", 3, 6500, 18, 9.0f, 25);
    public static final Item.ToolMaterial damascus_steel = EnumHelper.addToolMaterial("tfcflux_damascus_steel", 3, 9000, 25, 12.0f, 100);
}
