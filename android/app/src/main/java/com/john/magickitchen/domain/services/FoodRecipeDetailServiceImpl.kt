package com.john.magickitchen.domain.services

import com.john.magickitchen.domain.models.FoodRecipeDetail
import com.john.magickitchen.domain.repositories.foodRecipesDetail.FoodRecipeDetailService
import com.john.magickitchen.infraestructure.network.anticorruption.toFoodRecipeDetail
import com.john.magickitchen.infraestructure.network.daos.MagicKitchenDaoRetroFit
import com.john.magickitchen.infraestructure.network.response.MagicKitchenResponse
import javax.inject.Inject

class FoodRecipeDetailServiceImpl @Inject constructor(
    private val magicKitchenDaoRetroFit: MagicKitchenDaoRetroFit
) : FoodRecipeDetailService {

    override suspend fun getFoodRecipeById(id: Int): MagicKitchenResponse<FoodRecipeDetail> {
        return try {
            return when (val response =
                MagicKitchenResponse.validateResponse(magicKitchenDaoRetroFit.getFoodRecipeById(id))) {
                is MagicKitchenResponse.Success -> {
                    val foodRecipes = response.response.toFoodRecipeDetail()
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