package mrthomas20121.tfcflux_machine.objects;

import net.dries007.tfc.objects.blocks.BlocksTFC;
import net.dries007.tfc.util.block.Multiblock;
import net.minecraft.util.math.BlockPos;

public class Multiblocks {
    public static Multiblock coke_oven;

    static {
        coke_oven = new Multiblock()
                .match(new BlockPos(0, 0, 0), state -> state.getBlock() == BlocksTFC.FIRE_BRICKS)
                .match(new BlockPos(0, 1, 0), state -> state.getBlock() == BlocksTFC.FIRE_BRICKS)
                .match(new BlockPos(0, 1, 1), state -> state.getBlock() == BlocksTFC.FIRE_BRICKS)
                .match(new BlockPos(1, 1, 0), state -> state.getBlock() == BlocksTFC.FIRE_BRICKS)
                .match(new BlockPos(1, 0, 1), state -> state.getBlock() == BlocksTFC.FIRE_BRICKS)
                .match(new BlockPos(1, 1, 1), state -> state.getBlock() == BlocksTFC.FIRE_BRICKS);
    }
}
