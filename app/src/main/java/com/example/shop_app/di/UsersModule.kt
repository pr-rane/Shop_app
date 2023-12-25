package com.example.shop_app.di


import com.example.shop_app.data.api.RequestInterceptor
import com.example.shop_app.data.api.ShopAPI
import com.example.shop_app.data.api.ShopAuthAPI
import com.example.shop_app.data.repo.user.RemoteUserAuth
import com.example.shop_app.data.repo.user.UserAuthInterface
import com.example.shop_app.data.repo.user.responses.LoginResponse
import com.example.shop_app.utils.Constants.Base_Url
import com.example.shop_app.utils.datastore.PreferenceDataStore
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UsersModule {

    @Singleton
    @Provides
    fun provideUsersApi(): ShopAuthAPI {

        val interceptor = RequestInterceptor()

        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(Base_Url)
            .build()

        return retrofit.create(ShopAuthAPI::class.java)
    }


    @Singleton
    @Provides
    fun provideUserAuthenticator(shopAuthAPI: ShopAuthAPI,
    preferenceDataStore: PreferenceDataStore): UserAuthInterface<LoginResponse> =
        RemoteUserAuth(shopAuthAPI,preferenceDataStore)
}