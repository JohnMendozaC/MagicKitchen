package com.john.magickitchen.infraestructure.network.vos

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LocationFoodRecipeVo(
    val name : String,
    val latitude : Double,
    val longitude : Double
) : Parcelable