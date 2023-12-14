package com.example.shop_app.ui.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import androidx.fragment.app.Fragment
import com.example.shop_app.ShopApplication
import com.example.shop_app.di.component.ActivityComponent
import com.example.shop_app.di.component.AppComponent
import com.example.shop_app.ui.MainActivity


fun ImageView.loadImage(uri: String, circleCrop: Boolean = false) {
    if (circleCrop) {
        Glide.with(this).load(uri).circleCrop().into(this)
    } else {
        Glide.with(this).load(uri).into(this)
    }
}

fun Fragment.getActivityComponent(): ActivityComponent = (requireActivity() as MainActivity).activityComponent
