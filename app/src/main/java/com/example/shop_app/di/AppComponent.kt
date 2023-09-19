package com.example.shop_app.di

import com.example.api.ShopClient
import com.example.api.services.ShopAPI
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [ShopClient::class])
interface AppComponent {
    fun getShopAPI(): ShopAPI

}