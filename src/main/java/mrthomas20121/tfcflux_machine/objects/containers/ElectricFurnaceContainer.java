package mrthomas20121.tfcflux_machine.objects.containers;

import mrthomas20121.tfcflux_machine.objects.tiles.machines.TEElectricFurnace;
import net.dries007.tfc.objects.container.ContainerTE;
import net.dries007.tfc.objects.inventory.slot.SlotCallback;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class ElectricFurnaceContainer extends ContainerTE<TEElectricFurnace> {

    public ElectricFurnaceContainer(InventoryPlayer playerInventory, TEElectricFurnace te)
    {
        super(playerInventory, te);
    }

    @Override
    protected void addContainerSlots() {
        IItemHandler inventory = tile.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
        if(inventory != null)
        {
            addSlotToContainer(new SlotCallback(inventory, 0, 71, 20, tile));
            addSlotToContainer(new SlotCallback(inventory, 1, 89, 20, tile));
            addSlotToContainer(new SlotCallback(inventory, 2, 107, 20, tile));
            addSlotToContainer(new SlotCallback(inventory, 3, 71, 48, tile));
            addSlotToContainer(new SlotCallback(inventory, 4, 89, 48, tile));
            addSlotToContainer(new SlotCallback(inventory, 5, 107, 48, tile));
        }
    }
}
