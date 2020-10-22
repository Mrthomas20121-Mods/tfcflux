package mrthomas20121.tfcflux.client;

import mrthomas20121.tfcflux.api.type.Machine;
import mrthomas20121.tfcflux.client.gui.SteamCrusherGui;
import mrthomas20121.tfcflux.objects.containers.CrusherContainer;
import mrthomas20121.tfcflux.client.gui.CrusherGui;
import mrthomas20121.tfcflux.objects.containers.SteamCrusherContainer;
import mrthomas20121.tfcflux.objects.tiles.machines.CrusherTe;
import mrthomas20121.tfcflux.objects.tiles.steam_machines.SteamCrusherTe;
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
                CrusherTe crusherTe = (CrusherTe) te;
                return new CrusherContainer(player.inventory, crusherTe);
            case STEAM_CRUSHER:
                SteamCrusherTe steamCrusherTe = (SteamCrusherTe) te;
                return new SteamCrusherContainer(player.inventory, steamCrusherTe);
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
                CrusherTe crusherTe = (CrusherTe) te;
                return new CrusherGui(new CrusherContainer(player.inventory, crusherTe), player.inventory, crusherTe);
            case STEAM_CRUSHER:
                SteamCrusherTe steamCrusherTe = (SteamCrusherTe) te;
                return new SteamCrusherGui(new SteamCrusherContainer(player.inventory, steamCrusherTe), player.inventory, steamCrusherTe);
        }
        return null;
    }
}