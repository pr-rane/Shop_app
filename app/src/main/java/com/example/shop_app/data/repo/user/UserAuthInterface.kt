package com.example.shop_app.data.repo.user

import com.example.shop_app.data.repo.user.responses.LoginResponse
import com.example.shop_app.utils.UiState
import com.example.shop_app.utils.SafeApiCall
import com.google.firebase.auth.FirebaseUser


interface UserAuthInterface<out T> : SafeApiCall {

    suspend fun login(username: String, password: String): UiState<T>

    suspend fun signup(name: String,username: String,password: String):UiState<T>

    suspend fun logout()

    suspend fun saveUserToken(accessToken: String)

    suspend fun clearUserToken()

    suspend fun loadUserToken(): String?
}