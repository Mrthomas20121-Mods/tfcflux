package mrthomas20121.tfcflux.objects.items.block;

import net.dries007.tfc.objects.items.itemblock.ItemBlockTFC;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;

public class ItemBlockFlux extends ItemBlockTFC {
    public ItemBlockFlux(Block block, ResourceLocation registryName)
    {
        super(block);
        this.setRegistryName(registryName);
    }
}
