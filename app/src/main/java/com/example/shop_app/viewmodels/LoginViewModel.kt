package com.example.shop_app.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shop_app.annotations.ActivityScope
import com.example.shop_app.data.ProductsRepo
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@ActivityScope
class LoginViewModel @Inject constructor(private val productRepo:ProductsRepo) : ViewModel() {

    val user: LiveData<String?>
    get() = productRepo._userToken


    fun login(email: String, passwprd: String) = viewModelScope.launch {
        productRepo.login(email,passwprd)
    }

    fun logout() {
        productRepo._userToken.postValue(null)
    }





}