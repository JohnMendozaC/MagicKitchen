package com.john.magickitchen.application.di

import com.john.magickitchen.infraestructure.network.Api
import com.john.magickitchen.infraestructure.network.daos.MagicKitchenDaoRetroFit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
    @Singleton
    @Provides
    fun provideMovieDaoRetrofit(): MagicKitchenDaoRetroFit = Api.create()
}