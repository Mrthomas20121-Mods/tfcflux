package mrthomas20121.tfcflux.client.gui;

import mrthomas20121.tfcflux.TfcFlux;
import mrthomas20121.tfcflux.objects.tiles.steam_machines.SteamCrusherTe;
import net.dries007.tfc.client.gui.GuiContainerTE;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class SteamCrusherGui extends GuiContainerTE<SteamCrusherTe>
{
    private static final ResourceLocation background = new ResourceLocation(TfcFlux.MODID, "textures/gui/containers/crusher.png");

    public SteamCrusherGui(Container container, InventoryPlayer playerInv, SteamCrusherTe tileEntity)
    {
        super(container, playerInv, tileEntity, background);
    }


}