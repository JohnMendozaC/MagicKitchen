package com.john.magickitchen.infraestructure.network

import com.john.magickitchen.BuildConfig.BASE_URL
import com.john.magickitchen.infraestructure.network.daos.MagicKitchenDaoRetroFit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Api {

    fun create(): MagicKitchenDaoRetroFit {

        val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

        val client = OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()

        val baseUrl = Jarvis.getDecodeData(BASE_URL)

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MagicKitchenDaoRetroFit::class.java)
    }
}