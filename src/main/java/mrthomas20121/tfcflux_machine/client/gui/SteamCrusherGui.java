package mrthomas20121.tfcflux_machine.client.gui;

import mrthomas20121.tfcflux_machine.TfcFluxMachine;
import mrthomas20121.tfcflux_machine.objects.tiles.steam_machines.TESteamCrusher;
import net.dries007.tfc.client.gui.GuiContainerTE;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class SteamCrusherGui extends GuiContainerTE<TESteamCrusher>
{
    private static final ResourceLocation background = new ResourceLocation(TfcFluxMachine.MODID, "textures/gui/containers/crusher.png");

    public SteamCrusherGui(Container container, InventoryPlayer playerInv, TESteamCrusher tileEntity)
    {
        super(container, playerInv, tileEntity, background);
    }


}