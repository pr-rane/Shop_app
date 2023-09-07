package com.example.api

import com.example.api.services.ShopAPI
import com.example.api.services.ShopAuthAPI
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
object ShopClient {

    val okHttpBuilder = OkHttpClient.Builder()
        .readTimeout(5,TimeUnit.SECONDS)
        .connectTimeout(2,TimeUnit.SECONDS)

    @Singleton
    @Provides
    fun providesRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://fakestoreapi.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpBuilder.build())
            .build()

    }

    @Singleton
    @Provides
    fun providesShopAPI(retrofit: Retrofit):ShopAPI{
        return retrofit
            .create(ShopAPI::class.java)
    }
    @Singleton
    @Provides
    fun providesShopAuthAPI(retrofit: Retrofit):ShopAuthAPI{
        return retrofit
            .create(ShopAuthAPI::class.java)
    }




}