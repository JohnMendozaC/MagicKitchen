package com.john.magickitchen.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FoodRecipe(
    val id : Int,
    val name : String,
    val image : String
) : Parcelable
