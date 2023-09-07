package com.example.shop_app.di

import com.example.api.ShopClient
import com.example.shop_app.MainActivity
import com.example.shop_app.viewmodels.*
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ShopClient::class,ViewModelModule::class])
interface ApplicationComponent {

    fun inject(mainActivity: MainActivity)


//    fun viewModelsFactory(): ViewModelFactory

    fun getGalleryVM():GalleryViewModel
    fun getHomeVM():HomeViewModel
    fun getLoginVM():LoginViewModel
    fun getProductVM():ProductViewModel

}