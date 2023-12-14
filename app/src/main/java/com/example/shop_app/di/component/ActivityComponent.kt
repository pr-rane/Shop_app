package com.example.shop_app.di.component

import com.example.shop_app.ui.MainActivity
import com.example.shop_app.di.ActivityScope
import com.example.shop_app.di.module.ActivityModule
import com.example.shop_app.ui.base.ViewModelFactory
import dagger.Component

@ActivityScope
@Component(dependencies = [AppComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(mainActivity: MainActivity)

    fun viewModelsFactory():ViewModelFactory


}