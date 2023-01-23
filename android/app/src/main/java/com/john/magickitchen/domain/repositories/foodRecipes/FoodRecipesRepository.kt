package com.john.magickitchen.domain.repositories.foodRecipes

import com.john.magickitchen.domain.models.FoodRecipe
import com.john.magickitchen.infraestructure.network.response.MagicKitchenResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FoodRecipesRepository @Inject constructor(
    private val foodRecipesService: FoodRecipesService
) {

    suspend fun getFoodRecipes() =
        withContext(Dispatchers.IO) { foodRecipesService.getFoodRecipes() }

    suspend fun searchFoodRecipes(query: String?) =
        withContext(Dispatchers.IO) {
            when (val data = foodRecipesService.getFoodRecipes()) {
                is MagicKitchenResponse.Success -> {
                    containsFoodRecipe(data.response, query)
                }
                is MagicKitchenResponse.Error -> {
                    data
                }
            }
        }

    private fun containsFoodRecipe(
        foodRecipes: List<FoodRecipe>,
        query: String?
    ): MagicKitchenResponse<List<FoodRecipe>> {
        val foodRecipesQuery = if (query.isNullOrEmpty()) foodRecipes else foodRecipes.filter {
            it.name.contains(
                query,
                ignoreCase = true
            )
        }

        return if (foodRecipesQuery.isEmpty()) MagicKitchenResponse.Error() else MagicKitchenResponse.Success(
            foodRecipesQuery
        )
    }
}