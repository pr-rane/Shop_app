package com.example.api.services

import com.example.api.models.entities.LoginData
import com.example.api.models.entities.Product
import retrofit2.Response
import retrofit2.http.*

interface ShopAPI {

    @GET("products")
    suspend fun getAllProducts(
        @Query("limit")limit: Int? = null,
        @Query("sort")sort: String? = null,
    )
    :Response<List<Product>>

    @GET("products/{id}")
    suspend fun getProductById(
        @Path("id") id: Int
    )
    :Response<Product>

    @GET("products/categories")
    suspend fun getAllCategories()
    :Response<List<String>>

    @GET("products/category/{category}")
    suspend fun getProductsByCategory(
        @Path("category") category: String?
    ):Response<List<Product>>

    @POST("auth/login")
    suspend fun loginUser(
        @Body login: LoginData
    ):Response<String>









}