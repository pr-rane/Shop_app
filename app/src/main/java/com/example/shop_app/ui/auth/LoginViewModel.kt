package com.example.shop_app.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shop_app.data.ProductsRepo
import kotlinx.coroutines.launch

class LoginViewModel(private val productRepo:ProductsRepo) : ViewModel() {

    private val _user = MutableLiveData<String?>()

    val user: LiveData<String?> = _user

    fun login(email: String, passwprd: String) = viewModelScope.launch {
        productRepo.login(email,passwprd)?.let {
            _user.postValue(it)
        }
    }

    fun logout() {
        _user.postValue(null)
    }





}