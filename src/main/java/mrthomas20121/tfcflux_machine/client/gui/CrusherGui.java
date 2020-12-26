package mrthomas20121.tfcflux_machine.client.gui;

import mrthomas20121.tfcflux_machine.TfcFluxMachine;
import mrthomas20121.tfcflux_machine.objects.tiles.machines.TECrusher;
import net.dries007.tfc.client.gui.GuiContainerTE;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class CrusherGui extends GuiContainerTE<TECrusher>
{
    private static final ResourceLocation background = new ResourceLocation(TfcFluxMachine.MODID, "textures/gui/containers/crusher.png");

    public CrusherGui(Container container, InventoryPlayer playerInv, TECrusher tileEntity)
    {
        super(container, playerInv, tileEntity, background);
    }
}