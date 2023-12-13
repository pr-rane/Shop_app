package com.example.shop_app.data.repo

import androidx.lifecycle.MutableLiveData
import com.example.shop_app.data.models.entities.LoginData
import com.example.shop_app.data.models.entities.Product
import com.example.shop_app.data.api.ShopAPI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
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

    suspend fun getCategories(): Flow<List<String>> {
        return flow {
            emit(api.getAllCategories())
        }
    }


    suspend fun login(email: String, password: String){
        val result = api.loginUser(LoginData(email, password))
        if (result.isSuccessful && result.body()!=null){
            _userToken.postValue(result.body()!!.token)

        }
    }


}