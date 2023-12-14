package com.example.shop_app.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shop_app.data.repo.ProductsRepo
import com.example.shop_app.di.ActivityScope
import com.example.shop_app.ui.base.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

class GalleryViewModel(private val productRepo: ProductsRepo) : ViewModel() {
    private val _categories = MutableStateFlow<UiState<List<String>>>(UiState.Loading)

    val categories: StateFlow<UiState<List<String>>> = _categories


    init {
        viewModelScope.launch {
            productRepo.getCategories()
                .catch { e ->
                    _categories.value = UiState.Error(e.toString())
                }
                .collect {
                    _categories.value = UiState.Success(it)
                }
        }
    }






}