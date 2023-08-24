package com.example.api.services

import com.example.api.models.entities.Product
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ShopAuthAPI {

    @POST("products")
    suspend fun addProduct(
        @Body product: Product
    ): Response<Product>

}