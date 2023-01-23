package com.john.magickitchen.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LocationFoodRecipe(
    val name : String,
    val latitude : Double,
    val longitude : Double
) : Parcelable