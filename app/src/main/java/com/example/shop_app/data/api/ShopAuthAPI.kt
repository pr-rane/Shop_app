package com.example.shop_app.data.api

import com.example.shop_app.data.models.entities.Product
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import javax.inject.Singleton

@Singleton
interface ShopAuthAPI {

    @POST("products")
    suspend fun addProduct(
        @Body product: Product
    ): Response<Product>

}