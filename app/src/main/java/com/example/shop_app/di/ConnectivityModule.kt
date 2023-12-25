package com.example.shop_app.di

import android.content.Context
import com.example.shop_app.data.repo.connection.ConnectivityObserver
import com.example.shop_app.data.repo.connection.DefaultConnectivityObserver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ConnectivityModule {

    @Singleton
    @Provides
    fun provideConnectivityObserver(@ApplicationContext context: Context): ConnectivityObserver =
        DefaultConnectivityObserver(context)
}