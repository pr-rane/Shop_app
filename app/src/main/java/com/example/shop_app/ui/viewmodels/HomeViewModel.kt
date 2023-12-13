package com.example.shop_app.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shop_app.data.models.entities.Product
import com.example.shop_app.data.repo.ProductsRepo
import com.example.shop_app.di.ActivityScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@ActivityScope
class HomeViewModel @Inject constructor(private val productRepo: ProductsRepo) : ViewModel() {
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