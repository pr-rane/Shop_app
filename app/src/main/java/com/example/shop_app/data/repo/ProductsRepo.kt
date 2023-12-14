package com.example.shop_app.data.repo

import com.example.shop_app.data.api.ShopAPI
import com.example.shop_app.data.models.entities.LoginData
import com.example.shop_app.data.models.entities.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductsRepo @Inject constructor(private val api: ShopAPI) {

    suspend fun getProducts():Flow<List<Product>> {
        return flow {
            emit(api.getAllProducts())
        }
    }

    suspend fun getProduct(productId: Int):Flow<Product> {
        return flow {
            emit(api.getProductById(productId))
        }
    }

    suspend fun getProductsByCategory(category: String?):Flow<List<Product>> {
        return flow {
            emit(api.getProductsByCategory(category))
        }
    }


    suspend fun getCategories(): Flow<List<String>> {
        return flow {
            emit(api.getAllCategories())
        }
    }


    suspend fun login(email: String, password: String):Flow<String?>{
        return flow {
            emit(api.loginUser(LoginData(email, password)).token)
        }
    }


}