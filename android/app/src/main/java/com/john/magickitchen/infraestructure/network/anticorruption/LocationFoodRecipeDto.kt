package com.john.magickitchen.infraestructure.network.anticorruption

import com.john.magickitchen.domain.models.LocationFoodRecipe
import com.john.magickitchen.infraestructure.network.vos.LocationFoodRecipeVo

fun LocationFoodRecipeVo.toLocationFoodRecipe() =
    LocationFoodRecipe(
        name = name,
        latitude = latitude,
        longitude = longitude
    )

