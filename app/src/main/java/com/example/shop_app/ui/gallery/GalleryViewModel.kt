package com.example.shop_app.ui.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shop_app.data.ProductsRepo
import kotlinx.coroutines.launch

class GalleryViewModel(private val productRepo:ProductsRepo) : ViewModel() {

    private val _categories = MutableLiveData<List<String>>()

    val categories: LiveData<List<String>> = _categories

    fun fetchCategories() = viewModelScope.launch {
        productRepo.getCategories().let {
            _categories.postValue(it)
        }
    }





}