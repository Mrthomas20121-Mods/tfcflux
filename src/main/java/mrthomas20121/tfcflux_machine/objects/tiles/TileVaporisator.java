package mrthomas20121.tfcflux_machine.objects.tiles;

import net.dries007.tfc.objects.fluids.capability.FluidHandlerSided;
import net.dries007.tfc.objects.fluids.capability.FluidTankCallback;
import net.dries007.tfc.objects.fluids.capability.IFluidHandlerSidedCallback;
import net.dries007.tfc.objects.fluids.capability.IFluidTankCallback;
import net.dries007.tfc.objects.te.TETickableBase;
import net.dries007.tfc.util.calendar.CalendarTFC;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TileVaporisator extends TETickableBase implements IFluidTankCallback, IFluidHandlerSidedCallback {

    protected FluidTankCallback inputTank = new FluidTankCallback(this, 11, 8000);
    protected FluidTankCallback outputTank = new FluidTankCallback(this, 12, 15000);

    public TileVaporisator() {
        outputTank.setFluid(FluidRegistry.getFluidStack("steam", 0));
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        inputTank.readFromNBT(nbt.getCompoundTag("inputTank"));
        outputTank.readFromNBT(nbt.getCompoundTag("outputTank"));
    }

    @Nonnull
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt)
    {
        nbt.setTag("inputTank", inputTank.writeToNBT(new NBTTagCompound()));
        nbt.setTag("outputTank", outputTank.writeToNBT(new NBTTagCompound()));
        return super.writeToNBT(nbt);
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing)
    {
        return capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing)
    {
        if (capability == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY)
        {
            return (T) new FluidHandlerSided(this, inputTank, facing);
        }
        return super.getCapability(capability, facing);
    }

    @Override
    public void setAndUpdateFluidTank(int i) {
        markForBlockUpdate();
    }

    @Override
    public boolean canFill(FluidStack resource, EnumFacing enumFacing) {
        return resource.getFluid() == null || resource.getFluid().getName().equals("fresh_water");
    }

    @Override
    public boolean canDrain(EnumFacing enumFacing) {
        return outputTank.canDrain();
    }

    @Override
    public void update() {
        super.update();
        if(inputTank.getFluidAmount() >= 1000) {
            for(int i = 0; i<=100; i++) {
                if(i == 100) {
                    inputTank.drain(1000, false);
                    FluidStack fluid = outputTank.getFluid();
                    fluid.amount+=500;
                    outputTank.fill(fluid, false);
                }
            }
        }
    }
}
