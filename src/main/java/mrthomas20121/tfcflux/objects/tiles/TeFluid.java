package mrthomas20121.tfcflux.objects.tiles;

import net.dries007.tfc.objects.fluids.capability.FluidTankCallback;
import net.dries007.tfc.objects.fluids.capability.IFluidHandlerSidedCallback;
import net.dries007.tfc.objects.fluids.capability.IFluidTankCallback;
import net.dries007.tfc.objects.te.TEInventory;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

public class TeFluid extends TEInventory implements IFluidTankCallback, IFluidHandlerSidedCallback {

    private FluidTankCallback tank;

    public TeFluid(int slots, int capacity, int tankID)
    {
        super(slots);
        this.tank = new FluidTankCallback(this, tankID, capacity);
        this.tank.setTileEntity(this);
    }
    public TeFluid(int slots, int capacity, FluidStack fluidStack, int tankID)
    {
        super(slots);
        this.tank = new FluidTankCallback(this, tankID, capacity);
        this.tank.setFluid(fluidStack);
    }
    public TeFluid(int slots, int capacity, Fluid fluid, int amount, int tankID)
    {
        super(slots);
        this.tank = new FluidTankCallback(this, tankID, capacity);
        this.tank.setFluid(new FluidStack(fluid, amount));
    }
    public TeFluid(int slots, int tankID)
    {
        super(slots);
        this.tank = new FluidTankCallback(this,tankID,10000);
    }

    @Override
    public void setAndUpdateFluidTank(int fluidtankID) {
        markForSync();
    }

    @Override
    public boolean canFill(FluidStack resource, EnumFacing enumFacing) {
        return resource.getFluid() == null;
    }

    @Override
    public boolean canDrain(EnumFacing enumFacing) {
        return (enumFacing == EnumFacing.UP || enumFacing != EnumFacing.DOWN);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);
        tank.readFromNBT(nbt.getCompoundTag("tank"));
        if (tank.getFluidAmount() > tank.getCapacity())
        {
            FluidStack fluidStack = tank.getFluid();
            if (fluidStack != null)
            {
                fluidStack.amount = tank.getCapacity();
            }
            tank.setFluid(fluidStack);
        }
    }

    @Nonnull
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt)
    {
        nbt.setTag("tank", tank.writeToNBT(new NBTTagCompound()));
        return super.writeToNBT(nbt);
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
}
