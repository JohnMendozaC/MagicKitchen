package com.john.magickitchen.domain.repositories.foodRecipes

import com.john.magickitchen.domain.models.FoodRecipe
import com.john.magickitchen.infraestructure.network.response.MagicKitchenResponse

interface FoodRecipesService {
    suspend fun getFoodRecipes() : MagicKitchenResponse<List<FoodRecipe>>
}