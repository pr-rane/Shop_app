package com.example.shop_app.data.repo.connection

import kotlinx.coroutines.flow.Flow

interface ConnectivityObserver {
    fun observer(): Flow<Boolean>
}