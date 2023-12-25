package com.example.shop_app.data.api

import com.example.shop_app.data.models.entities.LoginData
import com.example.shop_app.data.models.entities.Product
import com.example.shop_app.data.models.responses.LoginResponse
import com.example.shop_app.data.models.responses.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import javax.inject.Singleton

@Singleton
interface ShopAuthAPI {


    @POST("auth/login")
    suspend fun loginUser(
        @Body login: LoginData
    ): LoginResponse


    @GET("users/{id}")
    suspend fun getUserById(
        @Path("id") id: Int
    ): LoginResponse
}