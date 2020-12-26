package mrthomas20121.tfcflux_core;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = TFCFluxCore.MOD_ID, name = TFCFluxCore.NAME, version = TFCFluxCore.VERSION)
public class TFCFluxCore {

    public static final String MOD_ID = "tfcflux_core";
    public static final String NAME = "TFC Flux: Core";
    public static final String VERSION = "1.0";

    @Mod.Instance
    public static TFCFluxCore instance;

    private static Logger logger;

    public static Logger getLogger() {
        return logger;
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {

    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {

    }

}
