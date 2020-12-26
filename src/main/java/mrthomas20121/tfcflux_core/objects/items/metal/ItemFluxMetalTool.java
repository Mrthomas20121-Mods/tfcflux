package mrthomas20121.tfcflux_core.objects.items.metal;

import net.dries007.tfc.api.types.Metal;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ItemFluxMetalTool extends ItemFluxMetal {

    public final ToolMaterial material;
    public final int harvestLevel;

    public ItemFluxMetalTool(Metal metal, ItemMetalType type)
    {
        super(metal, type);

        if (metal.getToolMetal() == null)
            throw new IllegalArgumentException("You can't make tools out of non tool metals.");
        material = metal.getToolMetal();
        harvestLevel = material.getHarvestLevel();
        setMaxStackSize(1);
        setMaxDamage(material.getMaxUses());
    }

    @Override
    public int getItemEnchantability()
    {
        return material.getEnchantability();
    }

    @Override
    public boolean canDestroyBlockInCreative(World world, BlockPos pos, ItemStack stack, EntityPlayer player)
    {
        return false;
    }

    @Override
    public boolean canApplyAtEnchantingTable(@Nonnull ItemStack stack, @Nonnull Enchantment enchantment)
    {
        if (enchantment.type == EnumEnchantmentType.BREAKABLE)
        {
            return true;
        }
        return false;
    }

    @Override
    public boolean canStack(ItemStack stack)
    {
        return false;
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

        ItemStack itemstack = player.getHeldItem(hand);

        if (!player.canPlayerEdit(pos.offset(facing), facing, itemstack))
        {
            return EnumActionResult.FAIL;
        }

        if(!worldIn.isRemote) {
            switch (type) {
                case WRENCH:
                    break;
            }
        }

        return EnumActionResult.PASS;
    }
}
