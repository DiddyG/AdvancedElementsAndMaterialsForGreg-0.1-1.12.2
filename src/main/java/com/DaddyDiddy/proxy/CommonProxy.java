package com.DaddyDiddy.proxy;

import com.DaddyDiddy.items.ModMaterials;
import gregtech.api.GregTechAPI;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = "aemg")
public class CommonProxy
{
    public void preload() {}
    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void registerMaterials(GregTechAPI.MaterialEvent event)
    {
        ModMaterials.init();
    }
}
