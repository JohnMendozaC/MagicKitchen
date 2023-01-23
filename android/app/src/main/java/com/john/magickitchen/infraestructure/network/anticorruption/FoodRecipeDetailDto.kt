package com.john.magickitchen.infraestructure.network.anticorruption

import com.john.magickitchen.domain.models.FoodRecipeDetail
import com.john.magickitchen.infraestructure.network.vos.FoodRecipeDetailVo

fun FoodRecipeDetailVo.toFoodRecipeDetail() =
    FoodRecipeDetail(
        id = id,
        name = name,
        image = image,
        preparationTime = preparationTime,
        category = category,
        ingredients = ingredients,
        description = description,
        location = location.toLocationFoodRecipe()
    )

