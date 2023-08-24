package com.example.shop_app.data

import com.example.api.ShopClient
import com.example.api.models.entities.LoginData
import com.example.api.services.ShopAPI

class ProductsRepo(private val api: ShopAPI) {


    suspend fun getProducts() = api.getAllProducts().body()

    suspend fun getProduct(productId: Int) = api.getProductById(productId)

    suspend fun getProductsByCategory(category: String?) =
        api.getProductsByCategory(category).body()

    suspend fun getCategories() = api.getAllCategories().body()

    suspend fun login(email: String, password: String): String? {
        val response = api.loginUser(LoginData(email, password))
        return response.body()?.token
    }


}