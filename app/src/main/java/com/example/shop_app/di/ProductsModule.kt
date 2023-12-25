package com.example.shop_app.di

import com.example.shop_app.data.api.ShopAPI
import com.example.shop_app.data.api.ShopAuthAPI
import com.example.shop_app.data.repo.product.ProductsRepoInterface
import com.example.shop_app.data.repo.product.RemoteProductsRepo
import com.example.shop_app.utils.Constants.Base_Url
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProductsModule {

    @Singleton
    @Provides
    fun provideProductsApi(): ShopAPI {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(Base_Url)
            .build()

        return retrofit.create(ShopAPI::class.java)
    }


    @Singleton
    @Provides
    fun provideProductsRepository(shopAPI: ShopAPI): ProductsRepoInterface =
        RemoteProductsRepo(shopAPI)
}