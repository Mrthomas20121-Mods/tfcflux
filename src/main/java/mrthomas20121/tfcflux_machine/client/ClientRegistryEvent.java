package mrthomas20121.tfcflux_machine.client;

import mrthomas20121.tfcflux_machine.TfcFluxMachine;
import mrthomas20121.tfcflux_machine.objects.blocks.FluxBlocks;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(modid = TfcFluxMachine.MODID)
public class ClientRegistryEvent {

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        for(Block block: FluxBlocks.getBlocks()) {
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0 , new ModelResourceLocation(block.getRegistryName(), "inventory"));
        }
    }
}
