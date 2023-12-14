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
    private val _products = MutableStateFlow<UiState<List<Product>>>(UiState.Loading)
    val products: StateFlow<UiState<List<Product>>> = _products

    init {
        viewModelScope.launch {
            productRepo.getProducts()
                .catch { e ->
                    _products.value = UiState.Error(e.toString())
                }
                .collect {
                    _products.value = UiState.Success(it)
                }
        }
    }

    fun fetchProductsByCategory(category: String?) =
        viewModelScope.launch {
            if (category == null) {
                productRepo.getProducts()
                    .catch { e ->
                        _products.value = UiState.Error(e.toString())
                    }
                    .collect {
                        _products.value = UiState.Success(it)
                    }
            } else {
                productRepo.getProductsByCategory(category)
                    .catch { e ->
                        _products.value = UiState.Error(e.toString())
                    }
                    .collect {
                        _products.value = UiState.Success(it)
                    }
            }
        }
}