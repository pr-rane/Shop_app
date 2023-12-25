package com.example.shop_app.data.repo.product

import com.example.shop_app.data.api.ShopAPI
import com.example.shop_app.data.repo.product.model.Product
import com.example.shop_app.utils.UiState


class RemoteProductsRepo(private val api: ShopAPI) :
    ProductsRepoInterface {

    override suspend fun getProducts(): UiState<List<Product>> {
        return safeApiCall { api.getAllProducts() }
    }


    override suspend fun getProductsByCategory(category: String?): UiState<List<Product>> {
        return safeApiCall {api.getProductsByCategory(category) }
    }

    override suspend fun getCategories(): UiState<List<String>> {
        return  safeApiCall {api.getAllCategories() }
    }

    override suspend fun getProduct(productId: Int): UiState<Product> {
        return safeApiCall { api.getProductById(productId) }
    }


}