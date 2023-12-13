package com.example.shop_app.di.module

import android.content.Context
import com.example.shop_app.ShopApplication
import com.example.shop_app.data.api.ShopClient
import com.example.shop_app.di.ApplicationContext
import com.example.shop_app.di.BaseUrl
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: ShopApplication) {

    @ApplicationContext
    @Provides
    fun provideContext(): Context {
        return application
    }

    @BaseUrl
    @Provides
    fun provideBaseUrl(): String = "https://fakestoreapi.com/"

    @Provides
    @Singleton
    fun provideMoshiConverterFactory(): MoshiConverterFactory = MoshiConverterFactory.create()

    @Provides
    @Singleton
    fun provideOkHttpBuilder(): OkHttpClient.Builder = OkHttpClient.Builder()
        .readTimeout(5, TimeUnit.SECONDS)
        .connectTimeout(2, TimeUnit.SECONDS)
    @Provides
    @Singleton
    fun provideNetworkService(
        @BaseUrl baseUrl: String,
        moshiConverterFactory: MoshiConverterFactory,
        okHttpBuilder: OkHttpClient.Builder
    ): ShopClient {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(moshiConverterFactory)
            .client(okHttpBuilder.build())
            .build()
            .create(ShopClient::class.java)
    }

}