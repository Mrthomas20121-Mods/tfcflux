package mrthomas20121.tfcflux.proxy;

import mrthomas20121.tfcflux.client.GuiProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import mrthomas20121.tfcflux.TfcFlux;

@Mod.EventBusSubscriber
public class CommonProxy {

    public void preInit(FMLPreInitializationEvent e) {
		
    }

    public void init(FMLInitializationEvent e) {
        NetworkRegistry.INSTANCE.registerGuiHandler(TfcFlux.instance, new GuiProxy());
    }

    public void postInit(FMLPostInitializationEvent e) {
    }
}