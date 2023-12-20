package com.example.shop_app.data.repo

import com.example.shop_app.data.api.ShopAPI
import com.example.shop_app.data.models.entities.LoginData
import com.example.shop_app.data.models.entities.Product
import com.example.shop_app.ui.base.UiState
import com.example.shop_app.utils.SafeApiCall
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductsRepo @Inject constructor(private val api: ShopAPI):SafeApiCall {
    private val _products = MutableStateFlow<UiState<List<Product>>>(UiState.Loading)
    val products: StateFlow<UiState<List<Product>>>
        get() = _products

    private val _product = MutableStateFlow<UiState<Product>>(UiState.Loading)
    val product: StateFlow<UiState<Product>>
    get() = _product

    private val _categories = MutableStateFlow<UiState<List<String>>>(UiState.Loading)
    val categories: StateFlow<UiState<List<String>>>
    get() = _categories


    private val _user = MutableStateFlow<UiState<String?>>(UiState.Loading)
    val user: StateFlow<UiState<String?>>
    get() = _user


    suspend fun getProducts() {
        _products.value = safeApiCall{ api.getAllProducts() }
}

    suspend fun getProduct(productId: Int) {
        _product.value = safeApiCall { api.getProductById(productId) }
    }


    suspend fun getProductsByCategory(category: String?) {
        _products.value =  safeApiCall {api.getProductsByCategory(category) }

    }


    suspend fun getCategories() {
        _categories.value =  safeApiCall {api.getAllCategories() }
    }


    suspend fun login(email: String, password: String)= safeApiCall{
        api.loginUser(LoginData(email, password)).token
    }

    fun logout() {
        _user.value = UiState.Success(null)
    }


}