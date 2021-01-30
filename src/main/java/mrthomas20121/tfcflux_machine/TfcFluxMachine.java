package mrthomas20121.tfcflux_machine;

import mrthomas20121.tfcflux_machine.types.DefaultFluids;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = TfcFluxMachine.MODID, name = TfcFluxMachine.NAME, version = TfcFluxMachine.VERSION)
public class TfcFluxMachine
{
    public static final String MODID = "tfcflux_machine";
    public static final String NAME = "TFC Flux: Machines";
    public static final String VERSION = "1.0.0";

    @Mod.Instance
    public static TfcFluxMachine instance;

    private static Logger logger;

    public static Logger getLogger() {
        return logger;
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        DefaultFluids.preInit();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    }
}