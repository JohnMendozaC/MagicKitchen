package com.john.magickitchen.application.di

import com.john.magickitchen.domain.repositories.foodRecipes.FoodRecipesService
import com.john.magickitchen.domain.repositories.foodRecipesDetail.FoodRecipeDetailService
import com.john.magickitchen.domain.services.FoodRecipeDetailServiceImpl
import com.john.magickitchen.domain.services.FoodRecipesServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class FoodRecipesModule {

    @Binds
    abstract fun provideFoodRecipesService(foodRecipesService: FoodRecipesServiceImpl): FoodRecipesService

    @Binds
    abstract fun provideFoodRecipeDetailService(foodRecipesDetailServiceImpl: FoodRecipeDetailServiceImpl): FoodRecipeDetailService
}