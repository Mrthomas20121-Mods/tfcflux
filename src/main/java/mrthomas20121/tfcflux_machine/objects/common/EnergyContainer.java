package mrthomas20121.tfcflux_machine.objects.common;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class EnergyContainer extends EnergyStorage implements INBTSerializable<NBTTagCompound>
{

    public EnergyContainer(@Nullable NBTTagCompound nbt)
    {
        this(10000, 10000, 0);
        deserializeNBT(nbt);
    }

    public EnergyContainer(int capacity, int maxReceive, int energy)
    {
        super(capacity, maxReceive, 0, energy);
    }

    public boolean consumeEnergy(int amount, boolean simulate)
    {
        if (amount > energy)
            return false;
        if (!simulate)
            energy -= amount;
        return true;
    }

    @SideOnly(Side.CLIENT)
    public void setEnergy(int amount)
    {
        energy = amount;
    }

    @Override
    public NBTTagCompound serializeNBT()
    {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setInteger("energy", energy);
        return nbt;
    }

    @Override
    public void deserializeNBT(@Nullable NBTTagCompound nbt)
    {
        if (nbt != null)
        {
            energy = nbt.getInteger("energy");
        }
    }
}