package mrthomas20121.tfcflux_machine.objects.blocks;

import mrthomas20121.tfcflux_core.objects.CreativeTabsTFCFlux;
import mrthomas20121.tfcflux_machine.TfcFluxMachine;
import mrthomas20121.tfcflux_machine.objects.blocks.tech.pump.BlockPump;
import mrthomas20121.tfcflux_machine.objects.tiles.TilePump;
import net.dries007.tfc.objects.items.itemblock.ItemBlockTFC;
import net.dries007.tfc.objects.te.TETickableBase;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;


@Mod.EventBusSubscriber(modid = TfcFluxMachine.MODID)
public class FluxBlocks {

    private static ArrayList<Block> blocks = new ArrayList<>();

    public static ArrayList<Block> getBlocks() {
        return blocks;
    }

    @GameRegistry.ObjectHolder("tfcflux_machine:machine/pump")
    public static Block pump;

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        for(Block block: blocks) {
            ItemBlockTFC itemBlock = new ItemBlockTFC(block);
            itemBlock.setRegistryName(block.getRegistryName());
            itemBlock.setTranslationKey(block.getTranslationKey());
            itemBlock.setCreativeTab(block.getCreativeTab());
            event.getRegistry().register(itemBlock);
        }
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        IForgeRegistry<Block> r = event.getRegistry();
        blocks.add(register(r, new BlockPump(), "machine/pump"));
        TileEntity.register("tfcflux_machine:pump", TilePump.class);
    }

    public static Block register(IForgeRegistry<Block> r, Block block, String name) {
        block.setRegistryName(TfcFluxMachine.MODID, name);
        block.setTranslationKey(TfcFluxMachine.MODID+"_"+name.replace("/", "."));
        block.setCreativeTab(CreativeTabsTFCFlux.CT_Machines);
        r.register(block);
        return block;
    }
}
