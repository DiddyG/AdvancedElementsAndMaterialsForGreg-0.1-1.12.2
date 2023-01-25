package com.DaddyDiddy.items;

import com.DaddyDiddy.items.AEMGRecipeMap;
import gregtech.api.GTValues;
import gregtech.api.GregTechAPI;
import gregtech.api.items.metaitem.MetaItem;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.SimpleMachineMetaTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockAbilityPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.MultiblockControllerBase;
import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.RecipeMaps;
import gregtech.api.util.GTUtility;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import gregtech.integration.jei.multiblock.MultiblockInfoCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class ModMachines
{
    private static final Map<String, Boolean> MID_TIER = new HashMap<>();
    private static final Map<String, Boolean> HIGH_TIER = new HashMap<>();
    public static final SimpleMachineMetaTileEntity[] AEMG_INSCRIBER_MACHINE = new SimpleMachineMetaTileEntity[GTValues.V.length];
    public static final SimpleMachineMetaTileEntity[] AEMG_UUMATTER_EXTRACTOR = new SimpleMachineMetaTileEntity[GTValues.V.length];
    public static final SimpleMachineMetaTileEntity[] AEMG_UUMATTER_SOLIDIFIER = new SimpleMachineMetaTileEntity[GTValues.V.length];

    public static void init()
    {
        registerSimpleMetaTileEntity(AEMG_INSCRIBER_MACHINE, 30703, "inscriber_machine", AEMGRecipeMap.INSCRIBER_RECIPES, (ICubeRenderer) Textures.ALLOY_SMELTER_OVERLAY, true);
        registerSimpleMetaTileEntity(AEMG_UUMATTER_EXTRACTOR, 30718, "uumatter_extractor", AEMGRecipeMap.UUMATTER_EXTRACTOR_RECIPES, (ICubeRenderer) Textures.FLUID_HEATER_OVERLAY, true);
        registerSimpleMetaTileEntity(AEMG_UUMATTER_SOLIDIFIER, 30732, "uumatter_solidifier", AEMGRecipeMap.UUMATTER_SOLIDIFIER_RECIPES, (ICubeRenderer) Textures.FLUID_SOLIDIFIER_OVERLAY, true);
    }
    private static void registerSimpleMetaTileEntity(SimpleMachineMetaTileEntity[] machines, int startId, String name, RecipeMap<?> map, ICubeRenderer texture, boolean hasFrontFacing, Function<Integer, Integer> tankScalingFunction) {
        registerSimpleMetaTileEntity(machines, startId, name, map, texture, hasFrontFacing, ModMachines::gregtechId, tankScalingFunction);
    }
    private static void registerSimpleMetaTileEntity(SimpleMachineMetaTileEntity[] machines, int startId, String name, RecipeMap<?> map, ICubeRenderer texture, boolean hasFrontFacing) {
        registerSimpleMetaTileEntity(machines, startId, name, map, texture, hasFrontFacing, GTUtility.defaultTankSizeFunction);
    }
    public static void registerSimpleMetaTileEntity(SimpleMachineMetaTileEntity[] machines, int startId, String name, RecipeMap<?> map, ICubeRenderer texture, boolean hasFrontFacing, Function<String, ResourceLocation> resourceId, Function<Integer, Integer> tankScalingFunction) {
        for (int i = 0; i < machines.length - 1; i++) {
            if (i <= 4 || getMidTier(name)) {
                if (i > 14 && !getHighTier(name))
                    break;
                String voltageName = GTValues.VN[i + 1].toLowerCase();
                machines[i + 1] = registerMetaTileEntity(startId + i, new SimpleMachineMetaTileEntity(resourceId
                        .apply(String.format("%s.%s", new Object[] { name, voltageName })), map, texture, i + 1, hasFrontFacing, tankScalingFunction));
            }
        }
    }
    public static <T extends MetaTileEntity> T registerMetaTileEntity(int id, T sampleMetaTileEntity) {
        if (sampleMetaTileEntity instanceof IMultiblockAbilityPart) {
            IMultiblockAbilityPart<?> abilityPart = (IMultiblockAbilityPart)sampleMetaTileEntity;
            MultiblockAbility.registerMultiblockAbility(abilityPart.getAbility(), (MetaTileEntity)sampleMetaTileEntity);
        }
        if (sampleMetaTileEntity instanceof MultiblockControllerBase && Loader.isModLoaded("jei") && (
                (MultiblockControllerBase)sampleMetaTileEntity).shouldShowInJei())
            MultiblockInfoCategory.registerMultiblock((MultiblockControllerBase)sampleMetaTileEntity);
        GregTechAPI.MTE_REGISTRY.register(id, ((MetaTileEntity)sampleMetaTileEntity).metaTileEntityId, sampleMetaTileEntity);
        return sampleMetaTileEntity;
    }
    public static void setMidTier(String key, boolean enabled) {
        MID_TIER.put(key, Boolean.valueOf(enabled));
    }

    public static void setHighTier(String key, boolean enabled) {
        HIGH_TIER.put(key, Boolean.valueOf(enabled));
        GTValues.HT = (enabled || HIGH_TIER.containsValue(Boolean.valueOf(true)));
    }

    public static boolean getMidTier(String key) {
        return ((Boolean)MID_TIER.getOrDefault(key, Boolean.valueOf(true))).booleanValue();
    }

    public static boolean getHighTier(String key) {
        return ((Boolean)HIGH_TIER.getOrDefault(key, Boolean.valueOf(GTValues.HT))).booleanValue();
    }
    private static ResourceLocation gregtechId(String name) {
        return new ResourceLocation("gregtech", name);
    }
}
