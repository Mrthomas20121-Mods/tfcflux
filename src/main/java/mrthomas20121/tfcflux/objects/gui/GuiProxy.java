package mrthomas20121.tfcflux.objects.gui;

import mrthomas20121.tfcflux.objects.gui.containers.CrusherContainer;
import mrthomas20121.tfcflux.objects.gui.containers.CrusherContainerGui;
import mrthomas20121.tfcflux.objects.tiles.*;
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
        if (te instanceof CrusherTe) {
            return new CrusherContainer(player.inventory, (CrusherTe) te);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        TileEntity te = world.getTileEntity(pos);
        if (te instanceof CrusherTe) {
            CrusherTe containerTileEntity = (CrusherTe) te;
            return new CrusherContainerGui(containerTileEntity, new CrusherContainer(player.inventory, containerTileEntity));
        }
        return null;
    }
}