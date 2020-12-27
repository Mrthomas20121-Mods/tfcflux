package mrthomas20121.tfcflux_core.objects.items.metal;

import net.dries007.tfc.api.types.Metal;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import tfctech.objects.storage.MachineEnergyContainer;

import javax.annotation.Nullable;

public class ItemBattery extends ItemFluxMetal {

    private final MachineEnergyContainer energyContainer;

    public ItemBattery(Metal metal, ItemMetalType type) {
        super(metal, type);
        this.energyContainer = new MachineEnergyContainer(type.getSmeltAmount()*10, type.getSmeltAmount()*10, 0);
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable NBTTagCompound nbt) {
        return super.initCapabilities(stack, nbt);
    }

    public MachineEnergyContainer getEnergyContainer() {
        return energyContainer;
    }
}
