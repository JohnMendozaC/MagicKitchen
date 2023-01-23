package com.john.magickitchen.infraestructure.network.vos

import com.john.magickitchen.infraestructure.network.vos.LocationFoodRecipeVoDummies.getAnyLocationFoodRecipeVo

object FoodRecipeDetailVoDummies {

    private const val dummyId = 0
    private const val dummyName = "dummyname"
    private const val dummyImage = "dummyimage"
    private const val dummyPreparationTime = "dummypreparationTime"
    private const val dummyCategory = "dummycategory"
    private const val dummyIngredients = "dummyingredients"
    private const val dummyDescription = "dummydescription"

    fun getAnyFoodRecipeDetailVo() = FoodRecipeDetailVo(
        dummyId,
        dummyName,
        dummyImage,
        dummyPreparationTime,
        dummyCategory,
        dummyIngredients,
        dummyDescription,
        getAnyLocationFoodRecipeVo()
    )
}