package com.example.shop_app

import android.app.Application
import com.example.shop_app.di.ApplicationComponent
import com.example.shop_app.di.DaggerApplicationComponent


class ShopApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent
    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.builder().build()


    }

}