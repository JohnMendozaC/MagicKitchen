package com.john.magickitchen.domain.repositories.foodRecipes

import com.john.magickitchen.domain.models.FoodRecipe
import com.john.magickitchen.domain.models.FoodRecipeDummies.anyQuery
import com.john.magickitchen.domain.models.FoodRecipeDummies.getAnyListFoodRecipe
import com.john.magickitchen.domain.models.FoodRecipeDummies.getAnyListFoodRecipeWithAnyQuery
import com.john.magickitchen.infraestructure.network.response.MagicKitchenResponse
import io.kotest.matchers.shouldBe
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class FoodRecipesRepositoryTest {

    private lateinit var sut: FoodRecipesRepository

    @MockK(relaxed = true)
    private lateinit var foodRecipesServiceMock: FoodRecipesService

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        sut = FoodRecipesRepository(foodRecipesServiceMock)
    }

    @Test
    fun `Given a magic kitchen response success When get food recipes Then magic kitchen response should be the retrieved of service`() {
        `given a magic kitchen response success`()

        val response = `when get food recipes`()

        `then magic kitchen response should be the retrieved of service`(response)
    }

    private fun `given a magic kitchen response success`() {
        coEvery { foodRecipesServiceMock.getFoodRecipes() } returns MagicKitchenResponse.Success(
            getAnyListFoodRecipe()
        )
    }

    private fun `when get food recipes`(): MagicKitchenResponse<List<FoodRecipe>> = runBlocking {
        sut.getFoodRecipes()
    }

    private fun `then magic kitchen response should be the retrieved of service`(response: MagicKitchenResponse<List<FoodRecipe>>) {
        (response as MagicKitchenResponse.Success).response shouldBe getAnyListFoodRecipe()
    }

    @Test
    fun `Given a magic kitchen response error When get food recipes Then magic kitchen response should be the magic kitchen response error`() {
        `given a magic kitchen response error`()

        val response = `when get food recipes`()

        `then magic kitchen response should be the magic kitchen response error`(response)
    }

    private fun `given a magic kitchen response error`() {
        coEvery { foodRecipesServiceMock.getFoodRecipes() } returns MagicKitchenResponse.Error()
    }

    private fun `then magic kitchen response should be the magic kitchen response error`(response: MagicKitchenResponse<List<FoodRecipe>>) {
        response shouldBe MagicKitchenResponse.Error()
    }

    @Test
    fun `Given a magic kitchen response success When search food recipes Then magic kitchen response should be the retrieved of service`() {
        `given a magic kitchen response success`()

        val response = `when search food recipes`()

        `then magic kitchen response should be the retrieved of search service`(response)
    }

    private fun `when search food recipes`(): MagicKitchenResponse<List<FoodRecipe>> = runBlocking {
        sut.searchFoodRecipes(anyQuery)
    }

    private fun `then magic kitchen response should be the retrieved of search service`(response: MagicKitchenResponse<List<FoodRecipe>>) {
        (response as MagicKitchenResponse.Success).response shouldBe getAnyListFoodRecipeWithAnyQuery()
    }

    @Test
    fun `Given a magic kitchen response error When search food recipes Then magic kitchen response should be the magic kitchen response error`() {
        `given a magic kitchen response error`()

        val response = `when search food recipes`()

        `then magic kitchen response should be the magic kitchen response error`(response)
    }

}