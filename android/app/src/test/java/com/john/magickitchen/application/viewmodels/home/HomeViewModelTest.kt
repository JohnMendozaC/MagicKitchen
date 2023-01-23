package com.john.magickitchen.application.viewmodels.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.john.magickitchen.domain.repositories.foodRecipes.FoodRecipesRepository
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
class HomeViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val dispatcher = UnconfinedTestDispatcher()

    private lateinit var sut: HomeViewModel

    @MockK
    private lateinit var foodRecipesRepositoryMock: FoodRecipesRepository

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        MockKAnnotations.init(this)
        sut = HomeViewModel(foodRecipesRepositoryMock)
    }

    @After
    fun cleanup() {
        Dispatchers.resetMain()
    }

    @Test
    fun `Given a successful response When get food recipes Then status home view should be show food recipes`() {
        `given a successful response`()

        val response = `when get food recipes`()

        `then status home view should be show food recipes`(response)
    }

    @Test
    fun `Given a error response When get food recipes Then status home view should be show error`() {
        `given a error response`()

        val response = `when get food recipes`()

        `then status home view should be show error`(response)
    }

    private fun `given a successful response`() {
        coEvery { foodRecipesRepositoryMock.getFoodRecipes() } returns MagicKitchenResponse.Success(
            listOf()
        )
    }

    private fun `given a error response`() {
        coEvery { foodRecipesRepositoryMock.getFoodRecipes() } returns MagicKitchenResponse.Error()
    }


    private fun `when get food recipes`(): LiveData<StatusHomeView> {
        return sut.getFoodRecipes()
    }

    @Test
    fun `Given a successful search response When search food recipes Then status home view should be show food recipes`() {
        `given a successful search response`()

        val response = `when search food recipes`()

        `then status home view should be show food recipes`(response)
    }

    @Test
    fun `Given a error search response When search food recipes Then status home view should be show error`() {
        `given a error search response`()

        val response = `when search food recipes`()

        `then status home view should be show error`(response)
    }

    private fun `given a successful search response`() {
        coEvery { foodRecipesRepositoryMock.searchFoodRecipes(anyValue) } returns MagicKitchenResponse.Success(
            listOf()
        )
    }

    private fun `given a error search response`() {
        coEvery { foodRecipesRepositoryMock.searchFoodRecipes(anyValue) } returns MagicKitchenResponse.Error()
    }


    private fun `when search food recipes`(): LiveData<StatusHomeView> {
        return sut.searchFoodRecipes(anyValue)
    }

    private fun `then status home view should be show food recipes`(response: LiveData<StatusHomeView>) {
        val observer = mockk<Observer<StatusHomeView>>(relaxed = true)
        response.observeForever(observer)
        verifyOrder {
            observer.onChanged(StatusHomeView.ShowLoader(true))
            observer.onChanged(StatusHomeView.ShowFoodRecipes(listOf()))
            observer.onChanged(StatusHomeView.ShowLoader(false))
        }
    }

    private fun `then status home view should be show error`(response: LiveData<StatusHomeView>) {
        val observer = mockk<Observer<StatusHomeView>>(relaxed = true)
        response.observeForever(observer)
        verifyOrder {
            observer.onChanged(StatusHomeView.ShowLoader(true))
            observer.onChanged(StatusHomeView.ShowError())
            observer.onChanged(StatusHomeView.ShowLoader(false))
        }
    }

    companion object {
        const val anyValue = "dummyValue"
    }
}