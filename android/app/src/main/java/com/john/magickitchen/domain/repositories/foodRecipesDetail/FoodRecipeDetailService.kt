package com.john.magickitchen.domain.repositories.foodRecipesDetail

import com.john.magickitchen.domain.models.FoodRecipeDetail
import com.john.magickitchen.infraestructure.network.response.MagicKitchenResponse

interface FoodRecipeDetailService {
    suspend fun getFoodRecipeById(id: Int): MagicKitchenResponse<FoodRecipeDetail>
}