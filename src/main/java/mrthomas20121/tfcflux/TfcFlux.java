package mrthomas20121.tfcflux;

import mrthomas20121.tfcflux.proxy.CommonProxy;
import mrthomas20121.tfcflux.registry.Registry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = TfcFlux.MODID, name = TfcFlux.NAME, version = TfcFlux.VERSION)
public class TfcFlux
{
    public static final String MODID = "tfcflux";
    public static final String NAME = "TFC Flux";
    public static final String VERSION = "1.0";

    @Mod.Instance
    public static TfcFlux instance;

    private static Logger logger;

    public static Logger getLogger() {
        return logger;
    }

    @SidedProxy(serverSide = "mrthomas20121.tfcflux.proxy.CommonProxy", clientSide = "mrthomas20121.tfcflux.proxy.ClientProxy")
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        proxy.preInit(event);
        Registry.preInit();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
		proxy.init(event);
    }
}