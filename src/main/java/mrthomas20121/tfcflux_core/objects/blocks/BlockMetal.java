package mrthomas20121.tfcflux_core.objects.blocks;

import net.dries007.tfc.api.capability.metal.IMetalItem;
import net.dries007.tfc.api.capability.size.IItemSize;
import net.dries007.tfc.api.capability.size.Size;
import net.dries007.tfc.api.capability.size.Weight;
import net.dries007.tfc.api.types.Metal;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentTranslation;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class BlockMetal extends Block implements IMetalItem, IItemSize {

    private Map<Metal, EnumMap<BlockType, BlockMetal>> table = new HashMap<>();

    public Block get(Metal metal, BlockType type) {
        return table.get(metal).get(type);
    }

    public static Block create(Metal metal, BlockType type) {
        if(type.equals(BlockType.HARDENED_GLASS)) return new BlockHardenedGlass(metal);
        return new BlockMetal(metal, type);
    }

    protected final Metal metal;
    protected final  BlockType type;

    public BlockMetal(Metal metal, BlockType type) {
        super(Material.IRON);

        this.metal = metal;
        this.type = type;

        if(!table.containsKey(metal)) {
            table.put(metal, new EnumMap<>(BlockType.class));
        }
        table.get(metal).put(type, this);
    }

    @Nullable
    @Override
    public Metal getMetal(ItemStack itemStack) {
        return metal;
    }

    @Override
    public int getSmeltAmount(ItemStack itemStack) {
        return type.getSmeltAmount();
    }

    @Nonnull
    @Override
    public Size getSize(@Nonnull ItemStack itemStack) {
        return Size.NORMAL;
    }

    @Nonnull
    @Override
    public Weight getWeight(@Nonnull ItemStack itemStack) {
        return Weight.MEDIUM;
    }

    public BlockType getType() {
        return type;
    }

    public enum BlockType {
        FULL(900),
        CASING(900),
        HARDENED_GLASS(100);

        private int smeltAmount;

        BlockType(int smeltAmount) {
            this.smeltAmount = smeltAmount;
        }

        public int getSmeltAmount() {
            return smeltAmount;
        }
    }
}
