package com.example.shop_app.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api.models.entities.Product
import com.example.shop_app.annotations.ActivityScope
import com.example.shop_app.data.ProductsRepo
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@ActivityScope
class HomeViewModel @Inject constructor(private val productRepo:ProductsRepo) : ViewModel() {
    val products: LiveData<List<Product>>
    get() = productRepo._products

    init {
        viewModelScope.launch {
            productRepo.getProducts()
        }
    }

    fun fetchProductsByCategory(category: String?) = viewModelScope.launch {
        if (category == null) {
            productRepo.getProducts()
        } else {
            productRepo.getProductsByCategory(category)
        }
    }
}