package com.example.shop_app.utils

sealed interface UiState<out T> {

    data class Success<out T>(val data: T) : UiState<T>

    data class Error(val message: String) : UiState<Nothing>

    object Loading : UiState<Nothing>

    object Not_Started : UiState<Nothing>


}