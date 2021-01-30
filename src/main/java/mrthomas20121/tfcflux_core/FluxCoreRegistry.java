package mrthomas20121.tfcflux_core;

import mrthomas20121.tfcflux_core.objects.blocks.BlockMetal;
import mrthomas20121.tfcflux_core.objects.blocks.ItemBlockMetal;
import mrthomas20121.tfcflux_core.objects.items.metal.ItemFluxMetal;
import net.dries007.tfc.api.capability.metal.IMetalItem;
import net.dries007.tfc.api.registries.TFCRegistries;
import net.dries007.tfc.api.types.Metal;
import net.dries007.tfc.objects.items.itemblock.ItemBlockTFC;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

import javax.annotation.Nullable;
import java.awt.*;
import java.util.ArrayList;

@Mod.EventBusSubscriber(modid = TFCFluxCore.MOD_ID)
public class FluxCoreRegistry {

    private static ArrayList<Item> items = new ArrayList<>();
    private static ArrayList<Block> blocks = new ArrayList<>();

    public static ArrayList<Item> getItems() {
        return items;
    }

    public static ArrayList<Block> getBlocks() {
        return blocks;
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        IForgeRegistry<Item> r = event.getRegistry();

        for (Metal metal : TFCRegistries.METALS.getValuesCollection())
        {
            if(metal.isUsable())
            {
                items.add(register(r, ItemFluxMetal.create(metal, ItemFluxMetal.ItemMetalType.PLATE), "metal/"+ItemFluxMetal.ItemMetalType.PLATE.name().toLowerCase()+"/" + metal.getRegistryName().getPath()));
                items.add(register(r, ItemFluxMetal.create(metal, ItemFluxMetal.ItemMetalType.DOUBLE_PLATE), "metal/"+ItemFluxMetal.ItemMetalType.DOUBLE_PLATE.name().toLowerCase()+"/" + metal.getRegistryName().getPath()));
                if(metal.isToolMetal()) {
                    items.add(register(r, ItemFluxMetal.create(metal, ItemFluxMetal.ItemMetalType.WRENCH), "metal/"+ItemFluxMetal.ItemMetalType.WRENCH.name().toLowerCase()+"/" + metal.getRegistryName().getPath()));
                }
            }
        }
        Metal vanadium = TFCRegistries.METALS.getValue(TFCResources.vanadium);
        items.add(register(r, ItemFluxMetal.create(vanadium, ItemFluxMetal.ItemMetalType.BATTERY), "metal/battery/" + vanadium.getRegistryName().getPath()));

        for(Block block: blocks) {
            r.register(new ItemBlockMetal(block).setRegistryName(block.getRegistryName()).setTranslationKey(block.getTranslationKey()));
        }
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        IForgeRegistry<Block> r = event.getRegistry();

        for(Metal metal: TFCRegistries.METALS.getValuesCollection()) {
            for(BlockMetal.BlockType type : BlockMetal.BlockType.values()) {
                Block block = register(r, BlockMetal.create(metal, type), "metal/"+type.name().toLowerCase()+"/" + metal.getRegistryName().getPath());
                blocks.add(block);

            }
        }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void modelRegistry(ModelRegistryEvent event) {
        for(Block block: blocks) {
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(new ResourceLocation(TFCFluxCore.MOD_ID, block.getRegistryName().getPath()), "normal"));
        }
        for(Item item: items) {
            if(item instanceof ItemFluxMetal) {
                ItemFluxMetal itemFluxMetal = (ItemFluxMetal) item;
                ModelLoader.setCustomModelResourceLocation(itemFluxMetal, 0, new ModelResourceLocation(new ResourceLocation(TFCFluxCore.MOD_ID, "metal/"+itemFluxMetal.getType()), "inventory"));
            }
        }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void registerItemColourHandlers(final ColorHandlerEvent.Item event)
    {
        final ItemColors itemColors = event.getItemColors();

        for (Block block : blocks)
        {
            itemColors.registerItemColorHandler(
                    (stack, tintIndex) -> {
                        return (new Color(((IMetalItem) stack.getItem()).getMetal(stack).getColor())).getRGB();
                    },block
            );
        }
        for(Item item: items) {
            itemColors.registerItemColorHandler(
                    (stack, tintIndex) -> {
                        return (new Color(((IMetalItem) stack.getItem()).getMetal(stack).getColor())).getRGB();
                    },item
            );
        }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void registerColorHandlerBlocks(ColorHandlerEvent.Block event)
    {
        BlockColors blockColors = event.getBlockColors();
        for(Block block : blocks) {
            if(block instanceof IMetalItem)
            blockColors.registerBlockColorHandler((IBlockState state, @Nullable IBlockAccess worldIn, @Nullable BlockPos pos, int tintIndex) -> {
                    if(state.getBlock() instanceof IMetalItem) return (new Color(((IMetalItem) state.getBlock()).getMetal(null).getColor())).getRGB();
                    return 0;
                }, block);
        }
    }

    private static Block register(IForgeRegistry<Block> r, Block block, String name) {
        block.setRegistryName(TFCFluxCore.MOD_ID, name);
        block.setTranslationKey(TFCFluxCore.MOD_ID+"."+name.replace("/", "."));
        r.register(block);
        return block;
    }

    private static Item register(IForgeRegistry<Item> r, Item item, String name) {
        item.setRegistryName(TFCFluxCore.MOD_ID, name);
        item.setTranslationKey(TFCFluxCore.MOD_ID+"."+name.replace("/", "."));
        r.register(item);
        return item;
    }
}
