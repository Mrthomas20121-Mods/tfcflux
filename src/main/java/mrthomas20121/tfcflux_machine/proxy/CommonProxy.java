package mrthomas20121.tfcflux_machine.proxy;

import mrthomas20121.tfcflux_machine.client.GuiProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import mrthomas20121.tfcflux_machine.TfcFluxMachine;

@Mod.EventBusSubscriber
public class CommonProxy {

    public void preInit(FMLPreInitializationEvent e) {
		
    }

    public void init(FMLInitializationEvent e) {
        NetworkRegistry.INSTANCE.registerGuiHandler(TfcFluxMachine.instance, new GuiProxy());
    }

    public void postInit(FMLPostInitializationEvent e) {
    }
}