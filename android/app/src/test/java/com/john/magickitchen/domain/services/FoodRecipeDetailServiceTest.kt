package com.john.magickitchen.domain.services

import com.john.magickitchen.domain.models.FoodRecipeDetail
import com.john.magickitchen.domain.models.FoodRecipeDetailDummies.getAnyFoodRecipeDetail
import com.john.magickitchen.infraestructure.network.daos.MagicKitchenDaoRetroFit
import com.john.magickitchen.infraestructure.network.response.MagicKitchenResponse
import com.john.magickitchen.infraestructure.network.vos.FoodRecipeDetailVoDummies.getAnyFoodRecipeDetailVo
import io.kotest.matchers.shouldBe
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class FoodRecipeDetailServiceTest {

    private lateinit var sut: FoodRecipeDetailServiceImpl

    @MockK(relaxed = true)
    private lateinit var magicKitchenDaoRetroFitMock: MagicKitchenDaoRetroFit

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        sut = FoodRecipeDetailServiceImpl(magicKitchenDaoRetroFitMock)
    }

    @Test
    fun `Given a successful get food recipe by id of magic kitchen dao retrofit When get food recipe by id Then the response should be the magic kitchen response success with the data retrieved of service`() {
        `given a successful get food recipe by id of magic kitchen dao retrofit`()

        val response = `when get food recipe by id`()

        `then the response should be the magic kitchen response success with the data retrieved of service`(
            response
        )
    }

    private fun `given a successful get food recipe by id of magic kitchen dao retrofit`() {
        coEvery { magicKitchenDaoRetroFitMock.getFoodRecipeById(anyIdValue) } returns Response.success(
            getAnyFoodRecipeDetailVo()
        )
    }

    private fun `when get food recipe by id`() = runBlocking {
        sut.getFoodRecipeById(anyIdValue)
    }

    private fun `then the response should be the magic kitchen response success with the data retrieved of service`(
        response: MagicKitchenResponse<FoodRecipeDetail>
    ) {
        response shouldBe MagicKitchenResponse.Success(getAnyFoodRecipeDetail())
    }

    @Test
    fun `Given a error get food recipe by id of magic kitchen dao retrofit When get food recipe by id Then the response should be the magic kitchen response error with the data retrieved of service`() {
        `given a error get food recipe by id of magic kitchen dao retrofit`()

        val response = `when get food recipe by id`()

        `then the response should be the magic kitchen response error with the data retrieved of service`(
            response
        )
    }

    private fun `given a error get food recipe by id of magic kitchen dao retrofit`() {
        coEvery { magicKitchenDaoRetroFitMock.getFoodRecipeById(anyIdValue) } returns Response.error(
            404, "anyBody".toResponseBody()
        )
    }

    private fun `then the response should be the magic kitchen response error with the data retrieved of service`(
        response: MagicKitchenResponse<FoodRecipeDetail>
    ) {
        response shouldBe MagicKitchenResponse.Error()
    }

    @Test
    fun `Given any exception error When get food recipe by id Then the response should be the magic kitchen response error with the data retrieved of service`() {
        `given any exception error`()

        val response = `when get food recipe by id`()

        `then the response should be the magic kitchen response error with the data retrieved of service`(
            response
        )
    }

    private fun `given any exception error`() {
        coEvery { magicKitchenDaoRetroFitMock.getFoodRecipeById(anyIdValue) } throws Exception()
    }

    companion object {
        private const val anyIdValue = -1
    }
}