package mrthomas20121.tfcflux_core.util;

import mrthomas20121.tfcflux_core.objects.items.metal.ItemFluxMetal;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.energy.CapabilityEnergy;
import tfctech.objects.storage.MachineEnergyContainer;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class EnergyContainerItemWrapper implements ICapabilityProvider {

    private final MachineEnergyContainer energyContainer;

    public EnergyContainerItemWrapper(ItemFluxMetal.ItemMetalType type) {
        this.energyContainer = new MachineEnergyContainer(type.getSmeltAmount()*10, type.getSmeltAmount()*10, 0);
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        if(capability == CapabilityEnergy.ENERGY) return true;
        return false;
    }

    @SuppressWarnings("unchecked")
    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        return (T)energyContainer;
    }
}
