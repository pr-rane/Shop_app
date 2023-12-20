package com.example.shop_app.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shop_app.data.repo.ProductsRepo
import com.example.shop_app.ui.base.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class LoginViewModel(private val userRepo: ProductsRepo) : ViewModel() {
    val user: StateFlow<UiState<String?>> get() = userRepo.user

    fun login(email: String, passwprd: String) = viewModelScope.launch {
        userRepo.login(email,passwprd)
    }






}