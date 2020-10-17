package mrthomas20121.tfcflux.objects.tiles;

import mrthomas20121.tfcflux.objects.common.EnergyContainer;
import net.dries007.tfc.objects.te.ITileFields;
import net.dries007.tfc.objects.te.TEInventory;
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
public class TeEnergy extends TEInventory implements ITileFields {

    private final EnergyContainer energyContainer;

    public TeEnergy(int nbSlots)
    {
        super(nbSlots);
        energyContainer = new EnergyContainer(10000, 10000, 0);
    }
    public TeEnergy(int nbSlots, int capacity)
    {
        super(nbSlots);
        energyContainer = new EnergyContainer(capacity, capacity, 0);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
    }

    @Nonnull
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setTag("inventory", inventory.serializeNBT());
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
    @SuppressWarnings("unchecked")
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        if (capability == CapabilityEnergy.ENERGY)
        {
            return (T) this.energyContainer;
        }
        return super.getCapability(capability, facing);
    }

    @Override
    public void setField(int index, int value) {
        if(index == 0)
        {
            this.energyContainer.setEnergy(value);
        }
    }

    @Override
    public int getField(int index) {
        if(index == 0)
        {
            return this.getEnergyStored();
        }
        return 0;
    }

    public int getEnergyCap()
    {
        return energyContainer.getMaxEnergyStored();
    }

    public int getEnergyStored()
    {
        return energyContainer.getEnergyStored();
    }

    @Override
    public int getFieldCount() {
        return 1;
    }
}
