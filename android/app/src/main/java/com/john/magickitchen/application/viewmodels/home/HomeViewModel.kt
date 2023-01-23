package com.john.magickitchen.application.viewmodels.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.john.magickitchen.domain.models.FoodRecipe
import com.john.magickitchen.domain.repositories.foodRecipes.FoodRecipesRepository
import com.john.magickitchen.infraestructure.network.response.MagicKitchenResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val foodRecipesRepository: FoodRecipesRepository
) : ViewModel() {

    fun getFoodRecipes() = validateResponseOfFoodRecipes {
        foodRecipesRepository.getFoodRecipes()
    }

    fun searchFoodRecipes(query: String?) = validateResponseOfFoodRecipes {
        foodRecipesRepository.searchFoodRecipes(query)
    }

    private fun validateResponseOfFoodRecipes(responseFoodRecipes: suspend () -> MagicKitchenResponse<List<FoodRecipe>>) =
        liveData(Dispatchers.Main) {
            emit(StatusHomeView.ShowLoader(true))
            when (val data = responseFoodRecipes.invoke()) {
                is MagicKitchenResponse.Success -> {
                    emit(StatusHomeView.ShowFoodRecipes(data.response))
                    emit(StatusHomeView.ShowLoader(false))
                }
                is MagicKitchenResponse.Error -> {
                    emit(StatusHomeView.ShowError())
                    emit(StatusHomeView.ShowLoader(false))
                }
            }
        }
}