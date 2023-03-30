package com.example.shop_app.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api.models.entities.Product
import com.example.shop_app.data.ProductsRepo
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _products = MutableLiveData<List<Product>>()

    val products: LiveData<List<Product>> = _products

    fun fetchProducts() = viewModelScope.launch {
        ProductsRepo.getProducts().let {
            _products.postValue(it)
        }
    }
    fun fetchProductsByCategory(category: String?) = viewModelScope.launch {
        ProductsRepo.getProductsByCategory(category).let {
            _products.postValue(it)
        }
    }
}