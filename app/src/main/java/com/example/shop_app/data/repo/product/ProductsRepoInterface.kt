package com.example.shop_app.data.repo.product

import com.example.shop_app.data.models.entities.Product
import com.example.shop_app.ui.base.UiState
import com.example.shop_app.utils.SafeApiCall

interface ProductsRepoInterface : SafeApiCall {

    suspend fun getProducts(): UiState<List<Product>>
    suspend fun getProductsByCategory(category: String?): UiState<List<Product>>
    suspend fun getCategories(): UiState<List<String>>
    suspend fun getProduct(productId: Int): UiState<Product>
}