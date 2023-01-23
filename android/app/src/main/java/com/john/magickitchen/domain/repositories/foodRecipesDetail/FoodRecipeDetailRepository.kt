package com.john.magickitchen.domain.repositories.foodRecipesDetail

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FoodRecipeDetailRepository @Inject constructor(
    private val foodRecipeDetailService: FoodRecipeDetailService
) {

    suspend fun getFoodRecipeById(id: Int) =
        withContext(Dispatchers.IO) { foodRecipeDetailService.getFoodRecipeById(id) }
}