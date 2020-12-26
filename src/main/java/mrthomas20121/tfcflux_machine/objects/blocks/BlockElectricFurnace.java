package mrthomas20121.tfcflux_machine.objects.blocks;

import mrthomas20121.tfcflux_machine.TfcFluxMachine;
import mrthomas20121.tfcflux_machine.api.type.Machine;
import mrthomas20121.tfcflux_machine.objects.tiles.machines.TEElectricFurnace;
import net.dries007.tfc.util.Helpers;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.ParametersAreNonnullByDefault;

public class BlockElectricFurnace extends BlockMachine {

    public BlockElectricFurnace()
    {
        super(Machine.ELECTRIC_FURNACE);
    }

    @ParametersAreNonnullByDefault
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TEElectricFurnace();
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
        TEElectricFurnace te = Helpers.getTE(world, pos, TEElectricFurnace.class);
        if(te != null && !world.isRemote)
        {
            player.openGui(TfcFluxMachine.instance, machine.getId(), world, pos.getX(), pos.getY(), pos.getZ());
        }
        return true;
    }
}
