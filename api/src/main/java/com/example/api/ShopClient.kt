package com.example.api

import com.example.api.services.ShopAPI
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object ShopClient {

    val okHttpBuilder = OkHttpClient.Builder()
        .readTimeout(5,TimeUnit.SECONDS)
        .connectTimeout(2,TimeUnit.SECONDS)



    val retrofitBuilder = Retrofit.Builder()
        .baseUrl("https://fakestoreapi.com/")
        .addConverterFactory(MoshiConverterFactory.create())

    val publicApi = retrofitBuilder
        .client(okHttpBuilder.build())
        .build()
        .create(ShopAPI::class.java)





}