package com.example.shop_app.data.repo.user

import com.example.shop_app.data.api.ShopAuthAPI
import com.example.shop_app.data.models.entities.LoginData
import com.example.shop_app.data.models.responses.LoginResponse
import com.example.shop_app.ui.base.UiState


class RemoteUserAuth(private val authAPI: ShopAuthAPI) : UserAuthInterface {
    override suspend fun login(username: String, password: String): UiState<LoginResponse> {
        return safeApiCall { authAPI.loginUser(LoginData(username, password)) }
    }

}