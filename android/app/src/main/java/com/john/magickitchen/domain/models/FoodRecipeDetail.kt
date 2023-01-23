package com.john.magickitchen.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FoodRecipeDetail(
    val id : Int,
    val name : String,
    val image : String,
    val preparationTime: String,
    val category: String,
    val ingredients: String,
    val description : String,
    val location : LocationFoodRecipe
) : Parcelable