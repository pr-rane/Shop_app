package com.example.shop_app.di

import android.content.Context
import com.example.shop_app.data.repo.connection.ConnectivityObserver
import com.example.shop_app.data.repo.connection.DefaultConnectivityObserver
import com.example.shop_app.utils.datastore.PreferenceDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStorePrefModule {

    @Singleton
    @Provides
    fun provideDataStorePref(@ApplicationContext context: Context): PreferenceDataStore =
        PreferenceDataStore(context)
}