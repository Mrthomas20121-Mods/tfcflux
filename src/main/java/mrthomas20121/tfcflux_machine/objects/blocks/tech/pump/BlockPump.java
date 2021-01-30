package mrthomas20121.tfcflux_machine.objects.blocks.tech.pump;

import mrthomas20121.tfcflux_machine.objects.tiles.TilePump;
import net.dries007.tfc.api.capability.size.IItemSize;
import net.dries007.tfc.api.capability.size.Size;
import net.dries007.tfc.api.capability.size.Weight;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class BlockPump extends Block implements IItemSize {

    public BlockPump() {
        super(Material.IRON);
        this.setHarvestLevel("pickaxe", 1);
        this.setHardness(3);
    }

    @Override
    public int getStackSize(@Nonnull ItemStack stack) {
        return 1;
    }

    @Nonnull
    @Override
    public Size getSize(@Nonnull ItemStack itemStack) {
        return Size.NORMAL;
    }

    @Nonnull
    @Override
    public Weight getWeight(@Nonnull ItemStack itemStack) {
        return Weight.MEDIUM;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(@Nonnull World world, @Nonnull IBlockState state) {
        return new TilePump();
    }
}
