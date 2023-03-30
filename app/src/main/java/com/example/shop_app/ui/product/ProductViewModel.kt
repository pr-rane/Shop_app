package com.example.shop_app.ui.product

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api.ShopClient
import com.example.api.models.entities.Product
import kotlinx.coroutines.launch

class ProductViewModel : ViewModel() {

    val api = ShopClient.publicApi
    private val _product = MutableLiveData<Product>()

    val product: LiveData<Product> = _product

    fun fetchProduct(productId: Int) = viewModelScope.launch {
        val response = api.getProductById(productId)

        response.body()?.let {
            _product.postValue(it)
        }
    }
}