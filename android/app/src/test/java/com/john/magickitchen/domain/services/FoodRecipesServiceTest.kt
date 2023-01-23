package com.john.magickitchen.domain.services

import com.john.magickitchen.domain.models.FoodRecipe
import com.john.magickitchen.domain.models.FoodRecipeDetail
import com.john.magickitchen.domain.models.FoodRecipeDummies.getAnyListFoodRecipe
import com.john.magickitchen.infraestructure.network.daos.MagicKitchenDaoRetroFit
import com.john.magickitchen.infraestructure.network.response.MagicKitchenResponse
import com.john.magickitchen.infraestructure.network.vos.FoodRecipeVoDummies.getAnyListFoodRecipeVo
import io.kotest.matchers.shouldBe
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class FoodRecipesServiceTest {

    private lateinit var sut: FoodRecipesServiceImpl

    @MockK(relaxed = true)
    private lateinit var magicKitchenDaoRetroFitMock: MagicKitchenDaoRetroFit

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        sut = FoodRecipesServiceImpl(magicKitchenDaoRetroFitMock)
    }

    @Test
    fun `Given a successful get food recipes of magic kitchen dao retrofit When get food recipes Then the response should be the magic kitchen response success with the data retrieved of service`() {
        `given a successful get food recipes of magic kitchen dao retrofit`()

        val response = `when get food recipes`()

        `then the response should be the magic kitchen response success with the data retrieved of service`(
            response
        )
    }

    private fun `given a successful get food recipes of magic kitchen dao retrofit`() {
        coEvery { magicKitchenDaoRetroFitMock.getFoodRecipes() } returns Response.success(
            getAnyListFoodRecipeVo()
        )
    }

    private fun `when get food recipes`() = runBlocking {
        sut.getFoodRecipes()
    }

    private fun `then the response should be the magic kitchen response success with the data retrieved of service`(
        response: MagicKitchenResponse<List<FoodRecipe>>
    ) {
        response shouldBe MagicKitchenResponse.Success(getAnyListFoodRecipe())
    }

    @Test
    fun `Given a error get food recipe by id of magic kitchen dao retrofit When get food recipe by id Then the response should be the magic kitchen response error with the data retrieved of service`() {
        `given a error get food recipe by id of magic kitchen dao retrofit`()

        val response = `when get food recipes`()

        `then the response should be the magic kitchen response error with the data retrieved of service`(
            response
        )
    }

    private fun `given a error get food recipe by id of magic kitchen dao retrofit`() {
        coEvery { magicKitchenDaoRetroFitMock.getFoodRecipes() }  returns Response.error(
            404, "anyBody".toResponseBody()
        )
    }

    private fun `then the response should be the magic kitchen response error with the data retrieved of service`(
        response: MagicKitchenResponse<List<FoodRecipe>>
    ) {
        response shouldBe MagicKitchenResponse.Error()
    }

    @Test
    fun `Given any exception error When get food recipes Then the response should be the magic kitchen response error with the data retrieved of service`() {
        `given any exception error`()

        val response = `when get food recipes`()

        `then the response should be the magic kitchen response error with the data retrieved of service`(
            response
        )
    }

    private fun `given any exception error`() {
        coEvery { magicKitchenDaoRetroFitMock.getFoodRecipes() } throws Exception()
    }

}