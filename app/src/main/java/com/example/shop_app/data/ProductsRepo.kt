package com.example.shop_app.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.api.ShopClient
import com.example.api.models.entities.LoginData
import com.example.api.models.entities.Product
import com.example.api.services.ShopAPI
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton


class ProductsRepo @Inject constructor(private val api: ShopAPI) {
    val _products = MutableLiveData<List<Product>>()
    val _product = MutableLiveData<Product>()
    val _categories = MutableLiveData<List<String>>()
    val _userToken = MutableLiveData<String>()


    suspend fun getProducts(){
        val result = api.getAllProducts()
        if (result.isSuccessful && result.body()!=null){
            _products.postValue(result.body())

        }
    }

    suspend fun getProduct(productId: Int){
        val result = api.getProductById(productId)
        if (result.isSuccessful && result.body()!=null){
            _product.postValue(result.body())

        }
    }

    suspend fun getProductsByCategory(category: String?){
        val result = api.getProductsByCategory(category)
        if (result.isSuccessful && result.body()!=null){
            _products.postValue(result.body())

        }
    }

    suspend fun getCategories(){
        val result = api.getAllCategories()
        if (result.isSuccessful && result.body()!=null){
            _categories.postValue(result.body())

        }
    }


    suspend fun login(email: String, password: String){
        val result = api.loginUser(LoginData(email, password))
        if (result.isSuccessful && result.body()!=null){
            _userToken.postValue(result.body()!!.token)

        }
    }


}