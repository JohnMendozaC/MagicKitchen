package com.john.magickitchen.domain.repositories.foodRecipesDetail

import com.john.magickitchen.domain.models.FoodRecipeDetail
import com.john.magickitchen.domain.models.FoodRecipeDetailDummies.getAnyFoodRecipeDetail
import com.john.magickitchen.infraestructure.network.response.MagicKitchenResponse
import io.kotest.matchers.shouldBe
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class FoodRecipeDetailRepositoryTest {

    private lateinit var sut: FoodRecipeDetailRepository

    @MockK(relaxed = true)
    private lateinit var foodRecipeDetailServiceMock: FoodRecipeDetailService

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        sut = FoodRecipeDetailRepository(foodRecipeDetailServiceMock)
    }

    @Test
    fun `Given a magic kitchen response success When get food recipe by id Then magic kitchen response should be the retrieved of service`() {
        `given a magic kitchen response success`()

        val response = `when get food recipe by id`()

        `then magic kitchen response should be the retrieved of service`(response)
    }

    private fun `given a magic kitchen response success`() {
        coEvery { foodRecipeDetailServiceMock.getFoodRecipeById(anyIdValue) } returns MagicKitchenResponse.Success(
            getAnyFoodRecipeDetail()
        )
    }

    private fun `when get food recipe by id`(): MagicKitchenResponse<FoodRecipeDetail> =
        runBlocking {
            sut.getFoodRecipeById(anyIdValue)
        }

    private fun `then magic kitchen response should be the retrieved of service`(response: MagicKitchenResponse<FoodRecipeDetail>) {
        (response as MagicKitchenResponse.Success).response shouldBe getAnyFoodRecipeDetail()
    }

    @Test
    fun `Given a magic kitchen response error When get food recipe by id Then magic kitchen response should be the magic kitchen response error`() {
        `given a magic kitchen response error`()

        val response = `when get food recipe by id`()

        `then magic kitchen response should be the magic kitchen response error`(response)
    }

    private fun `given a magic kitchen response error`() {
        coEvery { foodRecipeDetailServiceMock.getFoodRecipeById(anyIdValue) } returns MagicKitchenResponse.Error()
    }

    private fun `then magic kitchen response should be the magic kitchen response error`(response: MagicKitchenResponse<FoodRecipeDetail>) {
        response shouldBe MagicKitchenResponse.Error()
    }

    companion object {
        private const val anyIdValue = -1
    }
}