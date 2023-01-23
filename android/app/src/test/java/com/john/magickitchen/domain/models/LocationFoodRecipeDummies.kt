package com.john.magickitchen.domain.models

object LocationFoodRecipeDummies {

    private const val dummyName = "dummyName"
    private const val dummyLatitude = 0.0
    private const val dummyLongitude = 0.0

    fun getAnyLocationFoodRecipe() = LocationFoodRecipe(
        dummyName,
        dummyLatitude,
        dummyLongitude
    )
}