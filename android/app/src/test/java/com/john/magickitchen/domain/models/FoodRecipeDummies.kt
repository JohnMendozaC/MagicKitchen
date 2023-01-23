package com.john.magickitchen.domain.models


object FoodRecipeDummies {

    private const val anyId = 0
    private const val anyName = "anyName"
    private const val anyImage = "anyImage"
    const val anyQuery = "anyQuery"

    fun getAnyListFoodRecipe() = listOf(
        FoodRecipe(
            anyId,
            anyName,
            anyImage
        ),
        FoodRecipe(
            anyId,
            anyQuery,
            anyImage
        )
    )

    fun getAnyListFoodRecipeWithAnyQuery() = listOf(
        FoodRecipe(
            anyId,
            anyQuery,
            anyImage
        )
    )

}