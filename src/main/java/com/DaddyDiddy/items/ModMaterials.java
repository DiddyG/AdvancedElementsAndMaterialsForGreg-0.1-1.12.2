package com.DaddyDiddy.items;

import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.info.MaterialFlag;
import gregtech.api.unification.material.info.MaterialFlags;
import gregtech.api.unification.material.info.MaterialIconSet;

public class ModMaterials
{
    //Elements
    public static Material starlighttitanium;
    public static Material collapsium;
    public static Material elementzero;
    public static Material quantonium;
    public static Material metal_X;
    public static Material ultronium;
    public static Material wishalloy;
    public static Material inertron;
    public static Material computronium;

    //ElementBase
    public static Material starbase;
    public static Material blackhole;
    public static Material baseX;

    public static void registerMaterials()
    {
        collapsium = new Material.Builder(30700, "collapsium").ingot().fluid().color(656215).iconSet(MaterialIconSet.BRIGHT).flags(new MaterialFlag[]{MaterialFlags.GENERATE_PLATE}).build();
        starbase = new Material.Builder(30701, "starbase").fluid().color(272626).fluidTemp(230843).build();
        blackhole = new Material.Builder(30702, "blackhole").fluid().color(0x020202).fluidTemp(999999998).build();
    }

    public ModMaterials() {}

    public static void init()
    {
    }

}
