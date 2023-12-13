package com.example.shop_app.di.module

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.shop_app.data.repo.ProductsRepo
import com.example.shop_app.di.ActivityContext
import com.example.shop_app.ui.base.ViewModelFactory
import com.example.shop_app.ui.viewmodels.GalleryViewModel
import com.example.shop_app.ui.viewmodels.HomeViewModel
import com.example.shop_app.ui.viewmodels.LoginViewModel
import com.example.shop_app.ui.viewmodels.ProductViewModel
import com.example.shop_app.viewmodels.*
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: AppCompatActivity) {

    @ActivityContext
    @Provides
    fun provideContext(): Context {
        return activity
    }

    @Provides
    fun providegalleryViewModel(productsRepo: ProductsRepo): GalleryViewModel {
        return ViewModelProvider(activity,
            ViewModelFactory(GalleryViewModel::class) {
                GalleryViewModel(productsRepo)
            })[GalleryViewModel::class.java]
    }

    @Provides
    fun providehomeViewModel(productsRepo: ProductsRepo): HomeViewModel {
        return ViewModelProvider(activity,
            ViewModelFactory(HomeViewModel::class) {
                HomeViewModel(productsRepo)
            })[HomeViewModel::class.java]
    }

    @Provides
    fun provideloginViewModel(productsRepo: ProductsRepo): LoginViewModel {
        return ViewModelProvider(activity,
            ViewModelFactory(LoginViewModel::class) {
                LoginViewModel(productsRepo)
            })[LoginViewModel::class.java]
    }

    @Provides
    fun provideproductViewModel(productsRepo: ProductsRepo): ProductViewModel {
        return ViewModelProvider(activity,
            ViewModelFactory(ProductViewModel::class) {
                ProductViewModel(productsRepo)
            })[ProductViewModel::class.java]
    }


//    @Provides
//    fun provideTopHeadlineAdapter() = TopHeadlineAdapter(ArrayList())

}