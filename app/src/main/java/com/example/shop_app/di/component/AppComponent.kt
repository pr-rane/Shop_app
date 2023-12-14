package com.example.shop_app.di.component

import android.content.Context
import com.example.shop_app.ShopApplication
import com.example.shop_app.data.api.ShopAPI
import com.example.shop_app.data.repo.ProductsRepo
import com.example.shop_app.di.ApplicationContext
import com.example.shop_app.di.module.ApplicationModule
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [ApplicationModule::class])
interface AppComponent {

    fun inject(application: ShopApplication)

    @ApplicationContext
    fun getContext(): Context

    fun getShopAPI(): ShopAPI

    fun getProductsRepo(): ProductsRepo
}