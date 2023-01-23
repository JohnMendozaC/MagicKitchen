package com.john.magickitchen.infraestructure.network.daos

import com.john.magickitchen.infraestructure.network.vos.FoodRecipeDetailVo
import com.john.magickitchen.infraestructure.network.vos.FoodRecipeVo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MagicKitchenDaoRetroFit {

    @GET("foodRecipes")
    suspend fun getFoodRecipes(): Response<List<FoodRecipeVo>>

    @GET("foodRecipe/{id}")
    suspend fun getFoodRecipeById(
        @Path("id") id: Int
    ): Response<FoodRecipeDetailVo>
}