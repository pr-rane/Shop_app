package com.example.shop_app.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shop_app.annotations.ActivityScope
import com.example.shop_app.data.ProductsRepo
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton
import kotlin.reflect.KClass

@ActivityScope
class ViewModelFactory @Inject constructor(private val viewModels: MutableMap<Class<out ViewModel>,
        Provider<ViewModel>>) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T = viewModels[modelClass]?.get() as T
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