package com.example.shop_app.data.repo.user

import com.example.shop_app.data.models.responses.LoginResponse
import com.example.shop_app.ui.base.UiState
import com.example.shop_app.utils.SafeApiCall


interface UserAuthInterface : SafeApiCall {

    suspend fun login(username: String, password: String): UiState<LoginResponse>

}