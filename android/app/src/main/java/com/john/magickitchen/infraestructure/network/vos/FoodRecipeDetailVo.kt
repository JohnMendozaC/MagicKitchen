package com.john.magickitchen.infraestructure.network.vos

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FoodRecipeDetailVo(
    val id: Int,
    val name: String,
    val image: String,
    val preparationTime: String,
    val category: String,
    val ingredients: String,
    val description: String,
    val location: LocationFoodRecipeVo
) : Parcelable