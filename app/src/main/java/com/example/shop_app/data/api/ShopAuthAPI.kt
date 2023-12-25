package com.example.shop_app.data.api

import com.example.shop_app.data.repo.user.model.LoginData
import com.example.shop_app.data.repo.user.request.UserRequest
import com.example.shop_app.data.repo.user.responses.LoginResponse
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

    @POST("users")
    suspend fun signup(
        @Body userRequest: UserRequest
    ): String
}