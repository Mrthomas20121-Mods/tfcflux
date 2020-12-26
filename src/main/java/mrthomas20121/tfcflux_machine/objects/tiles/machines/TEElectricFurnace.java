package mrthomas20121.tfcflux_machine.objects.tiles.machines;

import mrthomas20121.tfcflux_machine.objects.common.EnergyContainer;
import net.dries007.tfc.ConfigTFC;
import net.dries007.tfc.api.capability.food.CapabilityFood;
import net.dries007.tfc.api.capability.food.FoodTrait;
import net.dries007.tfc.api.capability.heat.CapabilityItemHeat;
import net.dries007.tfc.api.capability.heat.IItemHeat;
import net.dries007.tfc.api.recipes.heat.HeatRecipe;
import net.dries007.tfc.objects.te.ITileFields;
import net.dries007.tfc.objects.te.TETickableInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Arrays;

public class TEElectricFurnace extends TETickableInventory implements ITileFields {

    public static final int FIELD_TEMPERATURE = 0;
    public static final int SLOT_INPUT_MIN = 0;
    public static final int SLOT_INPUT_MAX = 3;

    private final HeatRecipe[] cachedRecipes = new HeatRecipe[3];
    private boolean requiresSlotUpdate = false;
    private float temperature; // Current Temperature
    private final EnergyContainer energyContainer;

    public TEElectricFurnace()
    {
        super(10);
        temperature = 0;
        energyContainer = new EnergyContainer(10000, 10000, 0);

        Arrays.fill(cachedRecipes, null);
    }

    @Override
    public void update() {
        super.update();
        if(!world.isRemote)
        {
            int energyUsage = (int)((100000*temperature) / 100);
            if (energyUsage < 1) energyUsage = 1;

            if(temperature > 0 && energyContainer.getEnergyStored() > 0)
            {
                for (int i = SLOT_INPUT_MIN; i < SLOT_INPUT_MAX; i++)
                {
                    ItemStack stack = inventory.getStackInSlot(i);
                    IItemHeat cap = stack.getCapability(CapabilityItemHeat.ITEM_HEAT_CAPABILITY, null);
                    if (cap != null)
                    {
                        float itemTemp = cap.getTemperature();
                        if (temperature > itemTemp && energyContainer.consumeEnergy(energyUsage, false))
                        {
                            float temp = cap.getTemperature() + 15f * cap.getHeatCapacity() * (float) ConfigTFC.Devices.TEMPERATURE.globalModifier;
                            cap.setTemperature(Math.min(temp, temperature));
                        }
                        handleGrillCooking(i, stack, cap);
                    }
                }
            }
        }
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
    public int getFieldCount() {
        return 1;
    }

    @Override
    public void setField(int index, int value) {
        if(index == FIELD_TEMPERATURE)
        {
            this.temperature = (float)value;
        }
    }

    @Override
    public int getField(int index) {
        if(index == FIELD_TEMPERATURE)
        {
            return (int) temperature;
        }
        else if(index == 1)
        {
            return (int)energyContainer.getEnergyStored();
        }
        return 0;
    }

    public int getEnergyCapacity() {
        return 10000;
    }

    @Override
    public void setAndUpdateSlots(int slot)
    {
        super.setAndUpdateSlots(slot);
        requiresSlotUpdate = true;
        this.markDirty();
        updateCachedRecipes();
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        temperature = nbt.getFloat("temperature");
        energyContainer.deserializeNBT(nbt.getCompoundTag("energy"));
        super.readFromNBT(nbt);

        updateCachedRecipes();
    }

    @Override
    @Nonnull
    public NBTTagCompound writeToNBT(NBTTagCompound nbt)
    {
        nbt.setFloat("temperature", temperature);
        nbt.setTag("energy", energyContainer.serializeNBT());
        return super.writeToNBT(nbt);
    }

    private void handleGrillCooking(int slot, ItemStack stack, IItemHeat heat)
    {
        HeatRecipe recipe = cachedRecipes[slot];
        if (recipe != null && recipe.isValidTemperature(heat.getTemperature()))
        {
            ItemStack output = recipe.getOutputStack(stack);
            CapabilityFood.applyTrait(output, FoodTrait.WOOD_GRILLED);
            inventory.setStackInSlot(slot, output);
            markForSync();
        }
    }

    private void updateCachedRecipes()
    {
        // cache heat recipes for each input
        for (int i = SLOT_INPUT_MIN; i < SLOT_INPUT_MAX; i++)
        {
            cachedRecipes[i] = null;
            ItemStack inputStack = inventory.getStackInSlot(i);
            if (!inputStack.isEmpty())
            {
                cachedRecipes[i] = HeatRecipe.get(inputStack);
            }
        }
    }
}
