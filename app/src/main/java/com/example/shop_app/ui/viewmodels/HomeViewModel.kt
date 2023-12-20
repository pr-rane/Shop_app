package com.example.shop_app.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shop_app.data.models.entities.Product
import com.example.shop_app.data.repo.ProductsRepo
import com.example.shop_app.ui.base.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(private val productRepo: ProductsRepo) : ViewModel() {
    val products: StateFlow<UiState<List<Product>>>
    get() = productRepo.products

    init {
        viewModelScope.launch {
            productRepo.getProducts()
        }
    }

    fun fetchProductsByCategory(category: String?) =
        viewModelScope.launch {
            if (category == null) {
                productRepo.getProducts()

            } else {
                productRepo.getProductsByCategory(category)
            }
        }
}