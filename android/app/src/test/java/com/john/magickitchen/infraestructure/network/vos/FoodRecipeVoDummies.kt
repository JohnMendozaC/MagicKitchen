package com.john.magickitchen.infraestructure.network.vos

object FoodRecipeVoDummies {

    private const val anyId = 0
    private const val anyName = "anyName"
    private const val anyImage = "anyImage"
    private const val anyQuery = "anyQuery"

    fun getAnyListFoodRecipeVo() = listOf(
        FoodRecipeVo(
            anyId,
            anyName,
            anyImage
        ),
        FoodRecipeVo(
            anyId,
            anyQuery,
            anyImage
        )
    )
}