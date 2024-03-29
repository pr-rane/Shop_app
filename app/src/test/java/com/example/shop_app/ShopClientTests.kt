package com.example.shop_app

import com.example.shop_app.data.api.ShopAuthAPI
import com.example.shop_app.data.repo.user.model.LoginData
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.Request
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class ShopClientTests {

    lateinit var api:ShopAuthAPI
    @Before
    fun setup(){
        api = Retrofit.Builder()
            .baseUrl("https://fakestoreapi.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor { chain ->
                        val originalRequest: Request = chain.request()
                        val compressedRequest = originalRequest.newBuilder()
                            .header("Content-Type", "application/json")
                            .header("Content-Length", "43")
                            .header("Host", originalRequest.url().host())
                            .build()

                        chain.proceed(compressedRequest)
                    }
                    .readTimeout(120, TimeUnit.SECONDS)
                .connectTimeout(120, TimeUnit.SECONDS).build())
            .build()
            .create(ShopAuthAPI::class.java)

    }

    @Test
    fun apitest(){
        runBlocking {
            val logindata = api.loginUser(LoginData("mor_2314", "83r5^_"))
            Assert.assertNotNull(logindata.token)
        }

    }


    @After
    fun destroy(){

    }


}