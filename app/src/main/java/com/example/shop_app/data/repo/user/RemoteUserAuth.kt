package com.example.shop_app.data.repo.user

import com.example.shop_app.data.api.ShopAuthAPI
import com.example.shop_app.data.repo.user.model.LoginData
import com.example.shop_app.data.repo.user.request.UserRequest
import com.example.shop_app.data.repo.user.responses.LoginResponse
import com.example.shop_app.utils.UiState
import com.example.shop_app.utils.datastore.PreferenceDataStore
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull


class RemoteUserAuth(private val authAPI: ShopAuthAPI,
                     private val preferences: PreferenceDataStore
                     ) : UserAuthInterface<LoginResponse> {

    override suspend fun login(username: String, password: String): UiState<LoginResponse> {
        return safeApiCall { authAPI.loginUser(LoginData(username, password)) }
    }

    override suspend fun signup(
        name: String,
        username: String,
        password: String
    ): UiState<LoginResponse> {
        //Endpoint is not working
//        val userId =  safeApiCall {
//            authAPI.signup(UserRequest(null,null,
//                null,password,null,username)) }
        return UiState.Success(LoginResponse(null,null))
    }

    override suspend fun logout() {

    }

    override suspend fun saveUserToken(accessToken: String) {
        preferences.saveUserToken(accessToken)
    }

    override suspend fun clearUserToken() {
        preferences.clear()
    }

    override suspend fun loadUserToken():String? {
        return preferences.accessToken.firstOrNull()
    }

}