package com.john.magickitchen.domain.models

import com.john.magickitchen.domain.models.LocationFoodRecipeDummies.getAnyLocationFoodRecipe

object FoodRecipeDetailDummies {

    private const val dummyId = 0
    private const val dummyName = "dummyname"
    private const val dummyImage = "dummyimage"
    private const val dummyPreparationTime = "dummypreparationTime"
    private const val dummyCategory = "dummycategory"
    private const val dummyIngredients = "dummyingredients"
    private const val dummyDescription = "dummydescription"

    fun getAnyFoodRecipeDetail() = FoodRecipeDetail(
        dummyId,
        dummyName,
        dummyImage,
        dummyPreparationTime,
        dummyCategory,
        dummyIngredients,
        dummyDescription,
        getAnyLocationFoodRecipe()
    )
}