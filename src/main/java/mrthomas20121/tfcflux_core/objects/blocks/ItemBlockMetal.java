package mrthomas20121.tfcflux_core.objects.blocks;

import net.dries007.tfc.api.capability.metal.IMetalItem;
import net.dries007.tfc.api.types.Metal;
import net.dries007.tfc.objects.items.itemblock.ItemBlockTFC;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentTranslation;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ItemBlockMetal extends ItemBlockTFC implements IMetalItem {

    private Metal metal;
    private BlockMetal.BlockType type;

    public ItemBlockMetal(Block block) {
        super(block);
        if(block instanceof BlockMetal) {
            this.metal = ((BlockMetal) block).getMetal(null);
            this.type = ((BlockMetal) block).getType();
        }
        if(block instanceof BlockHardenedGlass) {
            this.metal = ((BlockHardenedGlass) block).getMetal(null);
            this.type = ((BlockHardenedGlass) block).getType();
        }
    }

    @Nullable
    @Override
    public Metal getMetal(ItemStack itemStack) {
        return metal;
    }

    @Override
    public int getSmeltAmount(ItemStack itemStack) {
        return type.getSmeltAmount();
    }

    @Override
    @Nonnull
    public String getItemStackDisplayName(@Nonnull ItemStack stack)
    {
        String name = (new TextComponentTranslation("tfc.types.metal."+metal.getRegistryName().getPath().toLowerCase())).getFormattedText();
        return (new TextComponentTranslation("block.tfcflux_core.metal."+type.name().toLowerCase()+".name", name)).getFormattedText();
    }
}
