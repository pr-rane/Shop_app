package com.example.shop_app.di

import androidx.fragment.app.Fragment
import com.example.shop_app.annotations.FragmentScope
import com.example.shop_app.ui.home.HomeFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent(modules = [])
interface FragmentComponent {

    fun inject(someFragment: Fragment)

}
