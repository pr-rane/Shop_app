package com.example.shop_app.utils

import com.example.shop_app.ui.base.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


interface SafeApiCall {
    suspend fun <T> safeApiCall(
        apiCall: suspend () -> T
    ): UiState<T> {
        return withContext(Dispatchers.IO) {
            try {
                UiState.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                UiState.Error(throwable.message.toString())
                }
            }
        }
    }


