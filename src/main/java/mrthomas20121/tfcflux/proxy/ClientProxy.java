package mrthomas20121.tfcflux.proxy;

import mrthomas20121.tfcflux.TfcFlux;
import mrthomas20121.tfcflux.objects.items.metal.ItemFluxMetal;
import net.dries007.tfc.api.capability.metal.IMetalItem;
import net.dries007.tfc.objects.items.ItemTFC;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import mrthomas20121.tfcflux.registry.Registry;

import java.awt.*;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy extends CommonProxy {
    @Override
    public void preInit(FMLPreInitializationEvent e) {
        super.preInit(e);
    }
    @SideOnly(Side.CLIENT)
    @Override
    public void postInit(FMLPostInitializationEvent e) { }
    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        for(ItemTFC item: Registry.simpleItems) {
            ModelLoader.setCustomModelResourceLocation(item, 0 , new ModelResourceLocation(item.getRegistryName(), "inventory"));
        }
        for(ItemFluxMetal item : Registry.metalItems)
        {
            ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(new ResourceLocation(TfcFlux.MODID, "metal/" + item.getType().name().toLowerCase()), "inventory"));
        }
        for(Block block : Registry.blocks) {
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
        }
    }
    @SubscribeEvent
    public static void registerItemColourHandlers(final ColorHandlerEvent.Item event)
    {
        final ItemColors itemColors = event.getItemColors();

        for (ItemFluxMetal item : Registry.metalItems)
        {
            itemColors.registerItemColorHandler(
                    (stack, tintIndex) -> {
                        return (new Color(((IMetalItem) stack.getItem()).getMetal(stack).getColor())).brighter().getRGB();
                    },item
            );
        }
    }
}