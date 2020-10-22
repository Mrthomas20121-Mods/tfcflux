package mrthomas20121.tfcflux.objects.containers;

import mrthomas20121.tfcflux.objects.tiles.steam_machines.SteamCrusherTe;
import net.dries007.tfc.objects.container.ContainerTE;
import net.dries007.tfc.objects.inventory.slot.SlotCallback;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class SteamCrusherContainer extends ContainerTE<SteamCrusherTe>
{

    public SteamCrusherContainer(InventoryPlayer playerInventory, SteamCrusherTe te) {
        super(playerInventory, te);

    }

    @Override
    protected void addContainerSlots() {
        IItemHandler inventory = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
        if(inventory != null)
        {
            addSlotToContainer(new SlotCallback(inventory, 0, 47, 34, tile));
            addSlotToContainer(new SlotCallback(inventory, 1, 65, 34, tile));
            addSlotToContainer(new SlotCallback(inventory, 2, 113, 34, tile));
        }
    }
}
