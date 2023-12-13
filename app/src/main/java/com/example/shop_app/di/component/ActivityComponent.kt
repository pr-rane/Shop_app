package com.example.shop_app.di.component

import com.example.shop_app.ui.MainActivity
import com.example.shop_app.di.ActivityScope
import com.example.shop_app.di.module.ActivityModule
import dagger.Component

@ActivityScope
@Component(dependencies = [AppComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(mainActivity: MainActivity)

    @Component.Factory
    interface Factory{
        fun create(appComponent: AppComponent): ActivityComponent
    }

}