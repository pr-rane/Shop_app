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
class ProductViewModel @Inject constructor(private val productRepo:ProductsRepo) : ViewModel() {

    val product: LiveData<Product>
    get() = productRepo._product

    fun fetchProduct(productId: Int) = viewModelScope.launch {
        productRepo.getProduct(productId)
    }
}