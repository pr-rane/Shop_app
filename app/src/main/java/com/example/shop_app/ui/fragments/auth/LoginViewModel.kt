package com.example.shop_app.ui.fragments.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shop_app.data.repo.user.responses.LoginResponse
import com.example.shop_app.data.repo.user.UserAuthInterface
import com.example.shop_app.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userAuthInterface: UserAuthInterface<LoginResponse>
    ) : ViewModel() {

    private val _user = MutableStateFlow<UiState<LoginResponse>>(UiState.Loading)
    val user: StateFlow<UiState<LoginResponse>>
        get() = _user


    fun login(email: String, password: String){
        _user.value = UiState.Loading
        viewModelScope.launch{
            _user.value = userAuthInterface.login(email,password)
        }
    }

    fun loadUserToken(){
        viewModelScope.launch {
            _user.value = UiState.Success(LoginResponse(
                userAuthInterface.loadUserToken()
            ))
        }
    }
    suspend fun saveUserToken(accessToken: String) {
        userAuthInterface.saveUserToken(accessToken)
    }

    fun logout() {
        _user.value = UiState.Success(LoginResponse())
    }

    suspend fun clearUserToken() {
        userAuthInterface.clearUserToken()
    }

}