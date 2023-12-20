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

class ProductViewModel(private val productRepo: ProductsRepo) : ViewModel() {
    val product: StateFlow<UiState<Product>>
        get() = productRepo.product
    fun fetchProduct(productId: Int) = viewModelScope.launch {
        productRepo.getProduct(productId)

    }
}