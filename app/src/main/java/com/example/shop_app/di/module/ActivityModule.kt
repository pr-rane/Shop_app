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
    fun provideViewModelFactory(productsRepo: ProductsRepo):ViewModelFactory{
        return ViewModelFactory(productsRepo)
    }

    @Provides
    fun providegalleryViewModel(viewModelFactory: ViewModelFactory): GalleryViewModel {
        return ViewModelProvider(activity,
            viewModelFactory)[GalleryViewModel::class.java]
    }

    @Provides
    fun providehomeViewModel(viewModelFactory: ViewModelFactory): HomeViewModel {
        return ViewModelProvider(activity,
            viewModelFactory)[HomeViewModel::class.java]
    }

    @Provides
    fun provideloginViewModel(viewModelFactory: ViewModelFactory): LoginViewModel {
        return ViewModelProvider(activity,
            viewModelFactory)[LoginViewModel::class.java]
    }

    @Provides
    fun provideproductViewModel(viewModelFactory: ViewModelFactory): ProductViewModel {
        return ViewModelProvider(activity,
            viewModelFactory)[ProductViewModel::class.java]
    }

}