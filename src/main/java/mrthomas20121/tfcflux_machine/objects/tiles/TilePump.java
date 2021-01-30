package mrthomas20121.tfcflux_machine.objects.tiles;

import net.dries007.tfc.objects.fluids.capability.FluidTankCallback;
import net.dries007.tfc.objects.fluids.capability.IFluidHandlerSidedCallback;
import net.dries007.tfc.objects.fluids.capability.IFluidTankCallback;
import net.dries007.tfc.objects.te.TETickableBase;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.*;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

public class TilePump extends TETickableBase implements IFluidTankCallback, IFluidHandlerSidedCallback {

    private static final int time = 40;
    private static final int genRate = 100*time;

    protected FluidTankCallback tank = new FluidTankCallback(this, 10, 10000);

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        tank.readFromNBT(nbt.getCompoundTag("tank"));
    }

    @ParametersAreNonnullByDefault
    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
            return true;
        }
        return super.hasCapability(capability, facing);
    }

    @ParametersAreNonnullByDefault
    @SuppressWarnings("unchecked")
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
            return (T)tank;
        }
        return super.getCapability(capability, facing);
    }

    @Nonnull
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt)
    {
        nbt.setTag("tank", tank.writeToNBT(new NBTTagCompound()));
        return super.writeToNBT(nbt);
    }

    @Override
    public void setAndUpdateFluidTank(int fluidtankID) {
        markForSync();
    }

    @Override
    public boolean canFill(FluidStack resource, EnumFacing enumFacing) {
        return resource.getFluid() == null || resource.getFluid().getBlock().getDefaultState().getMaterial().equals(Material.WATER);
    }

    @Override
    public boolean canDrain(EnumFacing enumFacing) {
        return true;
    }

    @Override
    public void update() {
        super.update();
        IBlockState stateDown = this.getBlockState(this.pos.down());
        if(isValidFluidBlock(stateDown)) {
            IFluidBlock blockFluid = getFluidBlock(stateDown);
            if(blockFluid != null) this.modifyFluidStored(blockFluid);

        }
    }

    private boolean isValidFluidBlock(IBlockState state) {
        return state.getBlock() instanceof IFluidBlock && state.getMaterial().equals(Material.WATER);
    }

    private IFluidBlock getFluidBlock(IBlockState state) {
        if(isValidFluidBlock(state)) return ((IFluidBlock)state.getBlock());
        return null;
    }

    private void modifyFluidStored(IFluidBlock block) {
        this.tank.fill(new FluidStack(block.getFluid(), 1000), true);
        block.drain(world, pos.down(), true);
    }

    private IBlockState getBlockState(BlockPos pos) {
        return this.world.getBlockState(pos);
    }
    
    private IBlockState getBlockState() {
        return this.world.getBlockState(pos);
    }
}
