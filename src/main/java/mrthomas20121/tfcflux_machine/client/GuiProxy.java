package mrthomas20121.tfcflux_machine.client;

import mrthomas20121.tfcflux_machine.api.type.Machine;
import mrthomas20121.tfcflux_machine.client.gui.ElectricFurnaceGui;
import mrthomas20121.tfcflux_machine.client.gui.SteamCrusherGui;
import mrthomas20121.tfcflux_machine.objects.containers.CrusherContainer;
import mrthomas20121.tfcflux_machine.client.gui.CrusherGui;
import mrthomas20121.tfcflux_machine.objects.containers.ElectricFurnaceContainer;
import mrthomas20121.tfcflux_machine.objects.containers.SteamCrusherContainer;
import mrthomas20121.tfcflux_machine.objects.tiles.machines.TECrusher;
import mrthomas20121.tfcflux_machine.objects.tiles.machines.TEElectricFurnace;
import mrthomas20121.tfcflux_machine.objects.tiles.steam_machines.TESteamCrusher;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiProxy implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntity te = world.getTileEntity(pos);
        Machine machine = Machine.from(ID);
        switch (machine)
        {
            case CRUSHER:
                TECrusher TECrusher = (TECrusher) te;
                return new CrusherContainer(player.inventory, TECrusher);
            case STEAM_CRUSHER:
                TESteamCrusher TESteamCrusher = (TESteamCrusher) te;
                return new SteamCrusherContainer(player.inventory, TESteamCrusher);
            case ELECTRIC_FURNACE:
                TEElectricFurnace electricFurnaceTe = (TEElectricFurnace) te;
                return new ElectricFurnaceContainer(player.inventory, electricFurnaceTe);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntity te = world.getTileEntity(pos);
        Machine machine = Machine.from(ID);
        switch (machine)
        {
            case CRUSHER:
                TECrusher TECrusher = (TECrusher) te;
                return new CrusherGui(new CrusherContainer(player.inventory, TECrusher), player.inventory, TECrusher);
            case STEAM_CRUSHER:
                TESteamCrusher TESteamCrusher = (TESteamCrusher) te;
                return new SteamCrusherGui(new SteamCrusherContainer(player.inventory, TESteamCrusher), player.inventory, TESteamCrusher);
            case ELECTRIC_FURNACE:
                TEElectricFurnace electricFurnaceTe = (TEElectricFurnace) te;
                return new ElectricFurnaceGui(new ElectricFurnaceContainer(player.inventory, electricFurnaceTe), player.inventory, electricFurnaceTe);
        }
        return null;
    }
}