package com.example.shop_app.data.api

import com.example.shop_app.data.models.entities.LoginData
import com.example.shop_app.data.models.entities.Product
import com.example.shop_app.data.models.responses.LoginResponse
import retrofit2.Response
import retrofit2.http.*
import javax.inject.Singleton

@Singleton
interface ShopAPI {

    @GET("products")
    suspend fun getAllProducts(
        @Query("limit")limit: Int? = null,
        @Query("sort")sort: String? = null
    )
    :Response<List<Product>>

    @GET("products/{id}")
    suspend fun getProductById(
        @Path("id") id: Int
    )
    :Response<Product>

    @GET("products/categories")
    suspend fun getAllCategories()
    :List<String>

    @GET("products/category/{category}")
    suspend fun getProductsByCategory(
        @Path("category") category: String?
    ):Response<List<Product>>

    @POST("auth/login")
    suspend fun loginUser(
        @Body login: LoginData
    ):Response<LoginResponse>


}