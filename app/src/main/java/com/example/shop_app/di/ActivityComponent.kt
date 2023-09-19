package com.example.shop_app.di

import com.example.api.ShopClient
import com.example.shop_app.MainActivity
import com.example.shop_app.annotations.ActivityScope
import com.example.shop_app.viewmodels.*
import dagger.BindsInstance
import dagger.Component

@ActivityScope
@Component(dependencies = [AppComponent::class], modules = [ViewModelModule::class])
interface ActivityComponent {

    fun inject(mainActivity: MainActivity)

    @Component.Factory
    interface Factory{
        fun create(appComponent: AppComponent):ActivityComponent
    }


//    fun createFragmentComponent(): FragmentComponent

//    fun viewModelsFactory(): ViewModelFactory

//    fun getGalleryVM():GalleryViewModel
//    fun getHomeVM():HomeViewModel
//    fun getLoginVM():LoginViewModel
//    fun getProductVM():ProductViewModel


}