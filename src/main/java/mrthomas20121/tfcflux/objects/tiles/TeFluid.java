package mrthomas20121.tfcflux.objects.tiles;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidTankProperties;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

public class TeFluid extends TeBase {

    private FluidTank tank;

    public TeFluid(int capacity)
    {
        this.tank = new FluidTank(capacity);
    }
    public TeFluid(int capacity, FluidStack fluidStack)
    {
        this.tank = new FluidTank(fluidStack, capacity);
    }
    public TeFluid()
    {
        this.tank = new FluidTank(10000);
    }

    @Override
    public void readFromNBT(NBTTagCompound tag)
    {
        super.readFromNBT(tag);
        tank.readFromNBT(tag);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound tag)
    {
        tag = super.writeToNBT(tag);
        tank.writeToNBT(tag);
        return tag;
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
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
            return (T)tank;
        }
        return super.getCapability(capability, facing);
    }
}
