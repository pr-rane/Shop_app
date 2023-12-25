package com.example.shop_app.ui.fragments.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shop_app.data.repo.product.model.Product
import com.example.shop_app.data.repo.product.ProductsRepoInterface
import com.example.shop_app.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val productsRepoInterface: ProductsRepoInterface) :
    ViewModel() {

    private var _products = MutableStateFlow<UiState<List<Product>>>(UiState.Loading)
    val products: StateFlow<UiState<List<Product>>>
        get() = _products


    init {
        _products.value = UiState.Loading
        viewModelScope.launch {
            _products.value = productsRepoInterface.getProducts()
        }
    }

    fun fetchProductsByCategory(category: String?){
        _products.value = UiState.Loading
        viewModelScope.launch {
            _products.value = if (category == null) {
                productsRepoInterface.getProducts()
            } else {
                productsRepoInterface.getProductsByCategory(category)
            }
        }
    }

    fun updateProductListOnConnectionReestablish() {
        if (_products.value is UiState.Error) _products.value = UiState.Not_Started
    }


}