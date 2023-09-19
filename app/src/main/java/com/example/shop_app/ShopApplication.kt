package com.example.shop_app

import android.app.Application
import com.example.shop_app.di.ActivityComponent
import com.example.shop_app.di.AppComponent
import com.example.shop_app.di.DaggerAppComponent


class ShopApplication : Application() {

    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().build()


    }

}