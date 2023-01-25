package com.DaddyDiddy.AdvancedGreg;

import com.DaddyDiddy.items.ModMachines;
import com.DaddyDiddy.items.ModMaterials;
import com.DaddyDiddy.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;


@Mod(modid = AdvancedElementsAndMaterials.MODID, name = AdvancedElementsAndMaterials.NAME, version = AdvancedElementsAndMaterials.VERSION)
public class AdvancedElementsAndMaterials
{
    public static final String MODID = "aemg";
    public static final String NAME = "Advanced Elements and Materials of Greg";
    public static final String VERSION = "0.1";

    @Mod.Instance
    public static AdvancedElementsAndMaterials instance;
    private static Logger logger;
    @SidedProxy(modId = "aemg", clientSide = "com.DaddyDiddy.proxy.ClientProxy", serverSide = "com.DaddyDiddy.proxy.CommonProxy")
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        proxy.preload();
        ModMaterials.registerMaterials();
        ModMachines.init();
    }
}
