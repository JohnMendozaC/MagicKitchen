package com.john.magickitchen.application.viewmodels.foodRecipeDetail

import com.john.magickitchen.domain.models.FoodRecipeDetail

sealed class StatusFoodRecipeDetail {
    data class ShowFoodRecipeDetail(val foodRecipeDetail: FoodRecipeDetail) : StatusFoodRecipeDetail()
    data class ShowLoader(val isLoading: Boolean) : StatusFoodRecipeDetail()
    data class ShowError(val codeError: Int = 0) : StatusFoodRecipeDetail()
}