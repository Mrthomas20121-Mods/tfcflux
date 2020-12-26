package mrthomas20121.tfcflux_core.objects.blocks;

import net.dries007.tfc.api.capability.metal.IMetalItem;
import net.dries007.tfc.api.capability.size.IItemSize;
import net.dries007.tfc.api.capability.size.Size;
import net.dries007.tfc.api.capability.size.Weight;
import net.dries007.tfc.api.types.Metal;
import net.minecraft.block.BlockGlass;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class BlockHardenedGlass extends BlockGlass implements IMetalItem, IItemSize {

    private final Metal metal;

    public BlockHardenedGlass(Metal metal)
    {
        super(Material.GLASS, false);
        this.metal = metal;
    }

    @Nullable
    @Override
    public Metal getMetal(ItemStack itemStack) {
        return metal;
    }

    @Override
    public int getSmeltAmount(ItemStack itemStack) {
        return 400;
    }

    public BlockMetal.BlockType getType() {
        return BlockMetal.BlockType.HARDENED_GLASS;
    }

    @Nonnull
    @Override
    public Weight getWeight(@Nonnull ItemStack itemStack) {
        return Weight.LIGHT;
    }

    @Nonnull
    @Override
    public Size getSize(@Nonnull ItemStack itemStack) {
        return Size.SMALL;
    }
}
