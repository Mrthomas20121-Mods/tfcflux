package mrthomas20121.tfcflux.objects.gui.containers;

import mrthomas20121.tfcflux.TfcFlux;
import mrthomas20121.tfcflux.objects.tiles.*;
import net.minecraft.util.ResourceLocation;

public class CrusherContainerGui extends DefaultGuiContainer {
    private static final int WIDTH = 176;
    private static final int HEIGHT = 152;

    private static final ResourceLocation background = new ResourceLocation(TfcFlux.MODID, "textures/gui/containers/crusher.png");

    public CrusherContainerGui(CrusherTe tileEntity, CrusherContainer container) {
        super(tileEntity, container, background, WIDTH, HEIGHT);
    }
}