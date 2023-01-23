package com.john.magickitchen.application.viewmodels.foodRecipeDetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.john.magickitchen.domain.models.FoodRecipeDetailDummies.getAnyFoodRecipeDetail
import com.john.magickitchen.domain.repositories.foodRecipesDetail.FoodRecipeDetailRepository
import com.john.magickitchen.infraestructure.network.response.MagicKitchenResponse
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verifyOrder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class FoodRecipeDetailViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val dispatcher = UnconfinedTestDispatcher()

    private lateinit var sut: FoodRecipeDetailViewModel

    @MockK
    private lateinit var foodRecipeDetailRepositoryMock: FoodRecipeDetailRepository

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        MockKAnnotations.init(this)
        sut = FoodRecipeDetailViewModel(foodRecipeDetailRepositoryMock)
    }

    @After
    fun cleanup() {
        Dispatchers.resetMain()
    }

    @Test
    fun `Given a successful response When get food recipe by id Then status home view should be show food recipe detail`() {
        `given a successful response`()

        val response = `when get food recipe by id`()

        `then status home view should be show food recipe detail`(response)
    }

    @Test
    fun `Given a error response When get food recipe by id Then status home view should be show error`() {
        `given a error response`()

        val response = `when get food recipe by id`()

        `then status home view should be show error`(response)
    }

    private fun `given a successful response`() {
        coEvery { foodRecipeDetailRepositoryMock.getFoodRecipeById(anyIdValue) } returns MagicKitchenResponse.Success(
            getAnyFoodRecipeDetail()
        )
    }

    private fun `given a error response`() {
        coEvery { foodRecipeDetailRepositoryMock.getFoodRecipeById(anyIdValue) } returns MagicKitchenResponse.Error()
    }

    private fun `when get food recipe by id`(): LiveData<StatusFoodRecipeDetail> {
        return sut.getFoodRecipeById(anyIdValue)
    }

    private fun `then status home view should be show food recipe detail`(response: LiveData<StatusFoodRecipeDetail>) {
        val observer = mockk<Observer<StatusFoodRecipeDetail>>(relaxed = true)
        response.observeForever(observer)
        verifyOrder {
            observer.onChanged(StatusFoodRecipeDetail.ShowLoader(true))
            observer.onChanged(StatusFoodRecipeDetail.ShowFoodRecipeDetail(getAnyFoodRecipeDetail()))
            observer.onChanged(StatusFoodRecipeDetail.ShowLoader(false))
        }
    }

    private fun `then status home view should be show error`(response: LiveData<StatusFoodRecipeDetail>) {
        val observer = mockk<Observer<StatusFoodRecipeDetail>>(relaxed = true)
        response.observeForever(observer)
        verifyOrder {
            observer.onChanged(StatusFoodRecipeDetail.ShowLoader(true))
            observer.onChanged(StatusFoodRecipeDetail.ShowError())
            observer.onChanged(StatusFoodRecipeDetail.ShowLoader(false))
        }
    }

    companion object {
        const val anyIdValue = -1
    }
}