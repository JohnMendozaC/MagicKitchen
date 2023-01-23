package com.john.magickitchen.infraestructure.network.anticorruption

import com.john.magickitchen.domain.models.FoodRecipe
import com.john.magickitchen.infraestructure.network.vos.FoodRecipeVo

fun List<FoodRecipeVo>.toFoodRecipeList() = this.map {
        FoodRecipe(
            id = it.id,
            name = it.name,
            image = it.image
        )
    }
