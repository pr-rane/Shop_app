package com.example.shop_app

import android.app.Application
import com.example.shop_app.di.component.AppComponent
import com.example.shop_app.di.component.DaggerAppComponent
import com.example.shop_app.di.module.ApplicationModule


class ShopApplication : Application() {

    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
        appComponent.inject(this)

    }

}