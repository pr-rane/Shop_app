package com.example.shop_app.ui.product

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api.models.entities.Product
import com.example.shop_app.data.ProductsRepo
import kotlinx.coroutines.launch

class ProductViewModel(private val productRepo:ProductsRepo) : ViewModel() {

    private val _product = MutableLiveData<Product>()

    val product: LiveData<Product> = _product

    fun fetchProduct(productId: Int) = viewModelScope.launch {
        val response = productRepo.getProduct(productId)

        response.body()?.let {
            _product.postValue(it)
        }
    }
}