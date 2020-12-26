package mrthomas20121.tfcflux_machine;

import mrthomas20121.tfcflux_machine.proxy.CommonProxy;
import mrthomas20121.tfcflux_machine.types.DefaultFluids;
import mrthomas20121.tfcflux_machine.types.DefaultRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = TfcFluxMachine.MODID, name = TfcFluxMachine.NAME, version = TfcFluxMachine.VERSION)
public class TfcFluxMachine
{
    public static final String MODID = "tfcflux_machine";
    public static final String NAME = "TFC Flux: Machines";
    public static final String VERSION = "1.0";

    @Mod.Instance
    public static TfcFluxMachine instance;

    private static Logger logger;

    public static Logger getLogger() {
        return logger;
    }

    @SidedProxy(serverSide = "mrthomas20121.tfcflux_machine.proxy.CommonProxy", clientSide = "mrthomas20121.tfcflux_machine.proxy.ClientProxy")
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        proxy.preInit(event);
        DefaultFluids.preInit();
        DefaultRegistry.preInit();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
		proxy.init(event);
    }
}