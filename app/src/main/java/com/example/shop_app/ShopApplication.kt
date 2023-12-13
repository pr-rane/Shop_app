package com.example.shop_app

import android.app.Application
import com.example.shop_app.di.component.AppComponent
import com.example.shop_app.di.component.DaggerAppComponent


class ShopApplication : Application() {

    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().build()
        appComponent.inject(this)

    }

}