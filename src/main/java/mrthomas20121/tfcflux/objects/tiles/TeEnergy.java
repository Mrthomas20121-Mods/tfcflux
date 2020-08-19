package mrthomas20121.tfcflux.objects.tiles;

import mrthomas20121.tfcflux.objects.common.EnergyContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.energy.CapabilityEnergy;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@SuppressWarnings("WeakerAccess")
@ParametersAreNonnullByDefault
public class TeEnergy extends TeBase {

    public static final int SIZE = 9;
    private EnergyContainer energyContainer;

    public TeEnergy()
    {
        energyContainer = new EnergyContainer(10000, 10000, 0);
    }
    public TeEnergy(int capacity)
    {
        energyContainer = new EnergyContainer(capacity, capacity, 0);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
    }

    @Nonnull
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        return compound;
    }

    @ParametersAreNonnullByDefault
    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        if(capability == CapabilityEnergy.ENERGY)
        {
            return true;
        }
        return super.hasCapability(capability, facing);
    }

    @ParametersAreNonnullByDefault
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        if (capability == CapabilityEnergy.ENERGY)
        {
            return (T) this.energyContainer;
        }
        return super.getCapability(capability, facing);
    }


}
