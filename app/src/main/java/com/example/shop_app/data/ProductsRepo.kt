package com.example.shop_app.data

import com.example.api.ShopClient
import com.example.api.models.entities.LoginData

object ProductsRepo {

    val api = ShopClient.publicApi

    suspend fun getProducts() = api.getAllProducts().body()

    suspend fun getProductsByCategory(category: String?) = api.getProductsByCategory(category).body()

    suspend fun getCategories() = api.getAllCategories().body()


    suspend fun login(email: String, password: String): String? {
        val response = api.loginUser(LoginData(email,password))
        return response.body()

    }



}