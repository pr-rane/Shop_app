package com.example.shop_app.data.api

import com.example.shop_app.data.repo.product.model.Product
import retrofit2.http.*
import javax.inject.Singleton

@Singleton
interface ShopAPI {

    @GET("products")
    suspend fun getAllProducts(
        @Query("limit")limit: Int? = null,
        @Query("sort")sort: String? = null
    )
    :List<Product>

    @GET("products/{id}")
    suspend fun getProductById(
        @Path("id") id: Int
    )
    : Product

    @GET("products/categories")
    suspend fun getAllCategories()
    :List<String>

    @GET("products/category/{category}")
    suspend fun getProductsByCategory(
        @Path("category") category: String?
    ):List<Product>




}