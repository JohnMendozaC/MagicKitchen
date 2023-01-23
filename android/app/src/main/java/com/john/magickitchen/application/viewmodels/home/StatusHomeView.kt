package com.john.magickitchen.application.viewmodels.home

import com.john.magickitchen.domain.models.FoodRecipe

sealed class StatusHomeView {
    data class ShowFoodRecipes(val foodRecipes: List<FoodRecipe>) : StatusHomeView()
    data class ShowLoader(val isLoading: Boolean) : StatusHomeView()
    data class ShowError(val codeError: Int = 0) : StatusHomeView()
}
