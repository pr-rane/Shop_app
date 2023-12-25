package com.example.shop_app.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.UnknownHostException


interface SafeApiCall {
    suspend fun <T> safeApiCall(
        apiCall: suspend () -> T
    ): UiState<T> {
        return withContext(Dispatchers.IO) {
            try {
                UiState.Success(apiCall.invoke())
            }
            catch (error: UnknownHostException){
                UiState.Error("There is no Internet connection")
            }
            catch (throwable: Throwable) {
                UiState.Error(throwable.message.toString())
                }
            }
        }
    }


