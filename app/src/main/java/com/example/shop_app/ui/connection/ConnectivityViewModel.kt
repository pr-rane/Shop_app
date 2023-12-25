package com.example.shop_app.ui.connection

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.shop_app.data.repo.connection.ConnectivityObserver
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ConnectivityViewModel @Inject constructor(connectivityObserver: ConnectivityObserver) : ViewModel() {

    val isConnected: LiveData<Boolean> =
        connectivityObserver.observer().asLiveData(viewModelScope.coroutineContext)
}