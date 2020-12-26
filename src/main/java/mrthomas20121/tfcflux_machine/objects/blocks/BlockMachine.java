package mrthomas20121.tfcflux_machine.objects.blocks;

import mrthomas20121.tfcflux_machine.api.type.Machine;
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
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import java.util.HashMap;
import java.util.Map;

import static mrthomas20121.tfcflux_machine.TfcFluxMachine.MODID;
import static mrthomas20121.tfcflux_core.objects.CreativeTabsTFCFlux.CT_Machines;

public class BlockMachine extends Block implements ITileEntityProvider, IItemSize {

    public static PropertyDirection FACING = PropertyDirection.create("facing");

    private static Map<Machine, BlockMachine> map = new HashMap<>();

    public static BlockMachine get(Machine machine)
    {
        return map.get(machine);
    }

    public static BlockMachine create(Machine machine)
    {
        switch (machine){
            case ELECTRIC_FURNACE:
                return new BlockElectricFurnace();
            case CRUSHER:
                return new BlockCrusher();
            case STEAM_CRUSHER:
                return new BlockSteamCrusher();
        }
        return null;
    }

    protected Machine machine;

    public BlockMachine(Machine machine) {
        super(Material.IRON);
        this.machine = machine;

        setCreativeTab(CT_Machines);
        setRegistryName(machine.getMachineName());
        setTranslationKey(MODID + "." + machine.getMachineName().replace('/', '.'));

        if (!map.containsKey(machine))
            map.put(machine, this);
    }

    @SuppressWarnings("ConstantConditions")
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
        return null;
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
