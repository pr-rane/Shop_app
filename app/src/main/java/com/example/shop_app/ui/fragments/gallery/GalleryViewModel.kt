package com.example.shop_app.ui.fragments.gallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shop_app.data.repo.product.ProductsRepoInterface
import com.example.shop_app.data.repo.product.RemoteProductsRepo
import com.example.shop_app.ui.base.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(private val productsRepoInterface: ProductsRepoInterface) : ViewModel() {
    private val _categories = MutableStateFlow<UiState<List<String>>>(UiState.Loading)
    val categories: StateFlow<UiState<List<String>>>
        get() = _categories

    init {
        getCategories()
    }


    fun getCategories(){
        viewModelScope.launch {
            _categories.value = productsRepoInterface.getCategories()
        }
    }

    fun updateProductListOnConnectionReestablish() {
        if (_categories.value is UiState.Error) _categories.value = UiState.Not_Started
    }





}