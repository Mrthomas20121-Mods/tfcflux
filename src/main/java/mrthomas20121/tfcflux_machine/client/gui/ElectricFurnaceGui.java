package mrthomas20121.tfcflux_machine.client.gui;

import mrthomas20121.tfcflux_machine.TfcFluxMachine;
import mrthomas20121.tfcflux_machine.objects.tiles.machines.TEElectricFurnace;
import net.dries007.tfc.api.capability.heat.Heat;
import net.dries007.tfc.client.gui.GuiContainerTE;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import tfctech.client.TechGuiHandler;

public class ElectricFurnaceGui extends GuiContainerTE<TEElectricFurnace> {

    private static final ResourceLocation background = new ResourceLocation(TfcFluxMachine.MODID, "textures/gui/containers/electric_furnace.png");

    public ElectricFurnaceGui(Container container, InventoryPlayer playerInv, TEElectricFurnace tileEntity)
    {
        super(container, playerInv, tileEntity, background);
    }

    @Override
    protected void renderHoveredToolTip(int mouseX, int mouseY)
    {
        if (mouseX >= guiLeft + 153 && mouseX <= guiLeft + 153 + 18 && mouseY >= guiTop + 6 && mouseY <= guiTop + 6 + 59)
        {
            int energy = tile.getField(1);
            drawHoveringText(I18n.format("tooltip.tfctech.gui.energy_format", energy, tile.getEnergyCapacity()), mouseX, mouseY);
        }
        super.renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);

        //Draw the temperature bar
        mc.getTextureManager().bindTexture(TechGuiHandler.GUI_ELEMENTS);
        drawTexturedModalRect(this.guiLeft + 11, this.guiTop + 17, 39, 1, 9, 52);

        // Draw the temperature indicator
        int targetTemperature = tile.getField(0);
        int temperaturePixels = (int) (51 * Math.min(Heat.maxVisibleTemperature(), targetTemperature) / Heat.maxVisibleTemperature()); //Max temperature is brilliant white in tfc
        drawTexturedModalRect(guiLeft + 8, guiTop + 66 - temperaturePixels, 36, 54, 15, 5);

        // Draw the energy bar
        int energyPixels = Math.round(60 * tile.getField(1) / (float)tile.getEnergyCapacity());
        int emptyPixels = 60 - energyPixels;
        drawTexturedModalRect(guiLeft + 153, guiTop + 6, 0, 0, 18, emptyPixels);
        drawTexturedModalRect(guiLeft + 153, guiTop + 6 + emptyPixels, 18, emptyPixels, 18, energyPixels);
    }
}
