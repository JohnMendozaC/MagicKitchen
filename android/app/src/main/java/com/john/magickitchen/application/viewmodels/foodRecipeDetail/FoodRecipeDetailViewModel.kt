package com.john.magickitchen.application.viewmodels.foodRecipeDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.john.magickitchen.domain.repositories.foodRecipesDetail.FoodRecipeDetailRepository
import com.john.magickitchen.infraestructure.network.response.MagicKitchenResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class FoodRecipeDetailViewModel @Inject constructor(
    private val foodRecipeDetailRepository: FoodRecipeDetailRepository
) : ViewModel() {

    fun getFoodRecipeById(id: Int) =
        liveData(Dispatchers.Main) {
            emit(StatusFoodRecipeDetail.ShowLoader(true))
            when (val data = foodRecipeDetailRepository.getFoodRecipeById(id)) {
                is MagicKitchenResponse.Success -> {
                    emit(StatusFoodRecipeDetail.ShowFoodRecipeDetail(data.response))
                    emit(StatusFoodRecipeDetail.ShowLoader(false))
                }
                is MagicKitchenResponse.Error -> {
                    emit(StatusFoodRecipeDetail.ShowError())
                    emit(StatusFoodRecipeDetail.ShowLoader(false))
                }
            }
        }
}