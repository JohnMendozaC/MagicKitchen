package com.john.magickitchen.domain.services

import com.john.magickitchen.domain.models.FoodRecipe
import com.john.magickitchen.domain.repositories.foodRecipes.FoodRecipesService
import com.john.magickitchen.infraestructure.network.anticorruption.toFoodRecipeList
import com.john.magickitchen.infraestructure.network.daos.MagicKitchenDaoRetroFit
import com.john.magickitchen.infraestructure.network.response.MagicKitchenResponse
import javax.inject.Inject

class FoodRecipesServiceImpl @Inject constructor(
    private val magicKitchenDaoRetroFit: MagicKitchenDaoRetroFit
) : FoodRecipesService {

    override suspend fun getFoodRecipes(): MagicKitchenResponse<List<FoodRecipe>> {
        return try {
            return when (val response =
                MagicKitchenResponse.validateResponse(magicKitchenDaoRetroFit.getFoodRecipes())) {
                is MagicKitchenResponse.Success -> {
                    val foodRecipes = response.response.toFoodRecipeList()
                    MagicKitchenResponse.Success(foodRecipes)
                }
                is MagicKitchenResponse.Error -> {
                    MagicKitchenResponse.Error()
                }
            }
        } catch (e: Exception) {
            MagicKitchenResponse.Error()
        }
    }
}