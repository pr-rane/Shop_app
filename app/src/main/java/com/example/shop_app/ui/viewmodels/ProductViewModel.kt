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
class ProductViewModel @Inject constructor(private val productRepo: ProductsRepo) : ViewModel() {

    val product: LiveData<Product>
    get() = productRepo._product

    fun fetchProduct(productId: Int) = viewModelScope.launch {
        productRepo.getProduct(productId)
    }
}