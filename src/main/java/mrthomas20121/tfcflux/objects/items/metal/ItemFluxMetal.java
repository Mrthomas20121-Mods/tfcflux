package mrthomas20121.tfcflux.objects.items.metal;

import java.util.EnumMap;
import java.util.HashMap;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import mrthomas20121.tfcflux.api.type.ItemMetalType;
import net.dries007.tfc.api.capability.forge.ForgeableHeatableHandler;
import net.dries007.tfc.api.capability.metal.IMetalItem;
import net.minecraft.item.ItemStack;
import net.dries007.tfc.api.capability.size.Size;
import net.dries007.tfc.api.capability.size.Weight;
import net.dries007.tfc.objects.items.ItemTFC;
import net.dries007.tfc.api.types.Metal;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

@SuppressWarnings("WeakerAccess")
@ParametersAreNonnullByDefault
public class ItemFluxMetal extends ItemTFC implements IMetalItem
{
    private static final HashMap<Metal, EnumMap<ItemMetalType, ItemFluxMetal>> MAP = new HashMap<>();

    public static ItemFluxMetal get(Metal metal, ItemMetalType type)
    {
        return MAP.get(metal).get(type);
    }

    public static ItemStack get(Metal metal, ItemMetalType type, int amount)
    {
        return new ItemStack(MAP.get(metal).get(type), amount);
    }

    private final Metal metal;
    private final ItemMetalType type;

    public ItemFluxMetal(Metal metal, ItemMetalType type)
    {
        this.metal = metal;
        this.type = type;
        if (!MAP.containsKey(metal))
            MAP.put(metal, new EnumMap<>(ItemMetalType.class));
        MAP.get(metal).put(type, this);
    }

    @Override
    @Nonnull
    public String getItemStackDisplayName(@Nonnull ItemStack stack)
    {
        String name = (new TextComponentTranslation("tfc.types.metal."+metal.getRegistryName().getPath().toLowerCase())).getFormattedText();
        return (new TextComponentTranslation("item.tfcflux.metal."+type.name().toLowerCase()+".name", name)).getFormattedText();
    }

    @Nonnull
    @Override
    public Size getSize(ItemStack stack)
    {
        switch(type){
            case PLATE:
            case DOUBLE_PLATE:
                return Size.NORMAL;
            case SMALL_DUST:
            default:
                return Size.SMALL;
        }
    }

    @Nonnull
    @Override
    public Weight getWeight(ItemStack stack)
    {
        switch(type){
            case PLATE:
            case DOUBLE_PLATE:
                return Weight.MEDIUM;
            case SMALL_DUST:
            default:
                return Weight.VERY_LIGHT;
        }
    }

    @Nonnull
    public Metal getMetal()
    {
        return metal;
    }

    @Nonnull
    @Override
    public Metal getMetal(ItemStack stack)
    {
        return metal;
    }

    @Nonnull
    public ItemMetalType getType()
    {
        return this.type;
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable NBTTagCompound nbt)
    {
        return new ForgeableHeatableHandler(nbt, metal.getSpecificHeat(), metal.getMeltTemp());
    }

    @Override
    public int getSmeltAmount(ItemStack itemStack)
    {
        return type.getSmeltAmount();
    }
}