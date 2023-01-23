package com.john.magickitchen.infraestructure.network.vos

object LocationFoodRecipeVoDummies {

    private const val dummyName = "dummyName"
    private const val dummyLatitude = 0.0
    private const val dummyLongitude = 0.0

    fun getAnyLocationFoodRecipeVo() = LocationFoodRecipeVo(
        dummyName,
        dummyLatitude,
        dummyLongitude
    )
}