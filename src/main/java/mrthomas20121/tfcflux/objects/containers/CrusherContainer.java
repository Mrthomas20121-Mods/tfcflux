package mrthomas20121.tfcflux.objects.containers;

import mrthomas20121.tfcflux.objects.tiles.machines.CrusherTe;
import net.dries007.tfc.objects.container.ContainerTE;
import net.dries007.tfc.objects.inventory.slot.SlotCallback;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class CrusherContainer extends ContainerTE<CrusherTe>
{

    public CrusherContainer(InventoryPlayer playerInventory, CrusherTe te) {
        super(playerInventory, te);

    }

    @Override
    protected void addContainerSlots() {
        IItemHandler inventory = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
        if(inventory != null)
        {
            addSlotToContainer(new SlotCallback(inventory, 0, 66, 19, tile));
            addSlotToContainer(new SlotCallback(inventory, 1, 66, 37, tile));
            addSlotToContainer(new SlotCallback(inventory, 2, 126, 28, tile));
        }
    }
}
