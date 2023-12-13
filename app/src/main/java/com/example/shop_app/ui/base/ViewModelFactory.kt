package com.example.shop_app.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Singleton
import kotlin.reflect.KClass

@Singleton
class ViewModelFactory<T:ViewModel>(
    private val kClass: KClass<T>,
    private val creator: () -> T) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    @Throws(IllegalArgumentException::class)
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(kClass.java)) return creator() as T
        throw IllegalArgumentException("Unknown class name")
    }
}


//class ViewModelFactory @Inject constructor(private val productRepo: ProductsRepo) : ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        return if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
//            LoginViewModel(productRepo) as T
//        }else if(modelClass.isAssignableFrom(GalleryViewModel::class.java)) {
//            GalleryViewModel(productRepo) as T
//        }else if(modelClass.isAssignableFrom(HomeViewModel::class.java)) {
//            HomeViewModel(productRepo) as T
//        }else if(modelClass.isAssignableFrom(ProductViewModel::class.java)) {
//            ProductViewModel(productRepo) as T
//        }else{
//            IllegalArgumentException("Error: Cannot create View-Model") as T
//        }
//    }
//}