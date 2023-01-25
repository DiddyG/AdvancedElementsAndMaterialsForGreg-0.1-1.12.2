package com.DaddyDiddy.items;

import gregtech.api.recipes.RecipeMap;
import gregtech.api.recipes.builders.SimpleRecipeBuilder;

public class AEMGRecipeMap
{

        public static final RecipeMap<SimpleRecipeBuilder> INSCRIBER_RECIPES = (new RecipeMap<>("inscriber_machine", 1, 3, 0, 1, 0, 0, 0, 0, new SimpleRecipeBuilder(), false));
        public static final RecipeMap<SimpleRecipeBuilder> UUMATTER_EXTRACTOR_RECIPES = (new RecipeMap<>("uumatter_extractor", 1, 1, 0, 0, 0, 0, 0, 1, new SimpleRecipeBuilder(), false));
        public static final RecipeMap<SimpleRecipeBuilder> UUMATTER_SOLIDIFIER_RECIPES = (new RecipeMap<>("uumatter_solidifier", 0, 1, 0, 1, 0, 1, 0, 0, new SimpleRecipeBuilder(), false));
}
