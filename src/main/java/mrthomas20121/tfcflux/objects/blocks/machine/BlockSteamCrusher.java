package mrthomas20121.tfcflux.objects.blocks.machine;

import mrthomas20121.tfcflux.TfcFlux;
import mrthomas20121.tfcflux.api.type.Machine;
import mrthomas20121.tfcflux.objects.tiles.steam_machines.SteamCrusherTe;
import net.dries007.tfc.api.capability.size.IItemSize;
import net.dries007.tfc.api.capability.size.Size;
import net.dries007.tfc.api.capability.size.Weight;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public class BlockSteamCrusher extends Block implements ITileEntityProvider, IItemSize {

    public static PropertyDirection FACING = PropertyDirection.create("facing");

    public BlockSteamCrusher(Material material) {
        super(material == null ? Material.IRON : material );
    }
    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
    @Nonnull
    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING);
    }
    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(FACING).getIndex();
    }
    @ParametersAreNonnullByDefault
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new SteamCrusherTe();
    }
    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
        // Only execute on the server
        if (world.isRemote) {
            return true;
        }
        TileEntity te = world.getTileEntity(pos);
        if (!(te instanceof SteamCrusherTe)) {
            return false;
        }
        player.openGui(TfcFlux.instance, Machine.STEAM_CRUSHER.getId(), world, pos.getX(), pos.getY(), pos.getZ());
        return true;
    }
    @Nonnull
    @Override
    public Size getSize(@Nonnull ItemStack itemStack)
    {
        return Size.LARGE;
    }

    @Nonnull
    @Override
    public Weight getWeight(@Nonnull ItemStack itemStack)
    {
        return Weight.MEDIUM;
    }

    @Override
    public boolean canStack(@Nonnull ItemStack stack)
    {
        return false;
    }
}
