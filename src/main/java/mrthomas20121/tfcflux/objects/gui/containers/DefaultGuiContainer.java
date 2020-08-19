package mrthomas20121.tfcflux.objects.gui.containers;

import mrthomas20121.tfcflux.objects.tiles.TeEnergy;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;

public class DefaultGuiContainer extends GuiContainer {

    protected ResourceLocation background;

    public DefaultGuiContainer(TeEnergy tileEntity, CrusherContainer container, ResourceLocation background, int width, int height)
    {
        super(container);
        this.background = background;

        this.xSize = width;
        this.ySize = height;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        mc.getTextureManager().bindTexture(background);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
    }
}
