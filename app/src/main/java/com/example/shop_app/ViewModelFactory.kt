package com.example.shop_app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shop_app.data.ProductsRepo
import com.example.shop_app.ui.auth.LoginViewModel
import com.example.shop_app.ui.gallery.GalleryViewModel
import com.example.shop_app.ui.home.HomeViewModel
import com.example.shop_app.ui.product.ProductViewModel

class ViewModelFactory(private val productRepo: ProductsRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            LoginViewModel(productRepo) as T
        }else if(modelClass.isAssignableFrom(GalleryViewModel::class.java)) {
            GalleryViewModel(productRepo) as T
        }else if(modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            HomeViewModel(productRepo) as T
        }else if(modelClass.isAssignableFrom(ProductViewModel::class.java)) {
            ProductViewModel(productRepo) as T
        }else{
            IllegalArgumentException("Error: Cannot create View-Model") as T
        }
    }
}