package com.john.magickitchen.infraestructure.network.vos

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FoodRecipeVo(
    val id : Int,
    val name : String,
    val image : String
) : Parcelable