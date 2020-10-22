package mrthomas20121.tfcflux.objects;

import net.dries007.tfc.api.capability.food.CapabilityFood;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

import static mrthomas20121.tfcflux.TfcFlux.MODID;

public final class CreativeTabsTFCFlux
{
    public static final CreativeTabs CT_Metals = new TFCFluxCreativeTab("metal", "tfcflux:metal/plate/lead");
    public static final CreativeTabs CT_Machines = new TFCFluxCreativeTab("machines", "tfcflux:metal/double_plate/lead");

    private static class TFCFluxCreativeTab extends CreativeTabs
    {
        private final ResourceLocation iconResourceLocation;

        private TFCFluxCreativeTab(String label, String icon)
        {
            super(MODID + "." + label);
            iconResourceLocation = new ResourceLocation(icon);
        }

        @SideOnly(Side.CLIENT)
        @Override
        @Nonnull
        public ItemStack createIcon()
        {
            //noinspection ConstantConditions
            ItemStack stack = new ItemStack(ForgeRegistries.ITEMS.getValue(iconResourceLocation));
            if (!stack.isEmpty())
            {
                // Food stacks shouldn't rot in creative tabs, and these are created on demand instead of beforehand and cached
                CapabilityFood.setStackNonDecaying(stack);
                return stack;
            }
            return new ItemStack(Items.STICK);
        }
    }
}