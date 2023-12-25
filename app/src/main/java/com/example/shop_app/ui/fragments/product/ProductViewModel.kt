package com.example.shop_app.ui.fragments.product

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
class ProductViewModel @Inject constructor(private val productsRepoInterface: ProductsRepoInterface) : ViewModel() {
    private val _product = MutableStateFlow<UiState<Product>>(UiState.Loading)
    val product: StateFlow<UiState<Product>>
        get() = _product

    fun fetchProduct(productId: Int) {
        _product.value = UiState.Loading
        viewModelScope.launch {
            _product.value = productsRepoInterface.getProduct(productId)
        }
    }


    fun updateProductListOnConnectionReestablish() {
        if (_product.value is UiState.Error) _product.value = UiState.Not_Started
    }

}