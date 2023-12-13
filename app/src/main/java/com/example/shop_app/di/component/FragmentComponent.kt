package com.example.shop_app.di.component

import androidx.fragment.app.Fragment
import com.example.shop_app.di.FragmentScope
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [])
interface FragmentComponent {

    fun inject(someFragment: Fragment)

}
