package mrthomas20121.tfcflux_machine.api.recipes;

import net.dries007.tfc.objects.inventory.ingredient.IIngredient;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.IForgeRegistryEntry;

import javax.annotation.Nullable;

public class CokeRecipe extends IForgeRegistryEntry.Impl<CokeRecipe> {

    private final IIngredient<ItemStack> input;
    private final ItemStack item_output;
    private final FluidStack fluid_output;

    public CokeRecipe(ResourceLocation registryName, IIngredient<ItemStack> input, ItemStack output, @Nullable FluidStack fluid_output) {
        this.input = input;
        this.item_output = output;
        this.fluid_output = fluid_output;

        this.setRegistryName(registryName);
    }

    public IIngredient<ItemStack> getInput() {
        return input;
    }

    public ItemStack getOutput() {
        return this.item_output;
    }

    public FluidStack getFluidOutput() {
        return fluid_output;
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public String toString()
    {
        return getRegistryName().getPath();
    }
}
