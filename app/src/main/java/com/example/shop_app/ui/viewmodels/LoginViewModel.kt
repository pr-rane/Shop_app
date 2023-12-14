package com.example.shop_app.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shop_app.data.repo.ProductsRepo
import com.example.shop_app.ui.base.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class LoginViewModel(private val productRepo: ProductsRepo) : ViewModel() {

    private val _user = MutableStateFlow<UiState<String?>>(UiState.Loading)

    val user: StateFlow<UiState<String?>> = _user


    fun login(email: String, passwprd: String) = viewModelScope.launch {
        productRepo.login(email,passwprd)
            .catch { e ->
                _user.value = UiState.Error(e.toString())
            }
            .collect {
                _user.value = UiState.Success(it)
            }
    }

    fun logout() {
        _user.value = UiState.Success(null)
    }





}