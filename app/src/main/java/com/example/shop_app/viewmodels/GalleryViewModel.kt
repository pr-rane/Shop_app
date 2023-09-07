package com.example.shop_app.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shop_app.data.ProductsRepo
import kotlinx.coroutines.launch
import javax.inject.Inject

class GalleryViewModel @Inject constructor(private val productRepo:ProductsRepo) : ViewModel() {
    val categories : LiveData<List<String>>
        get() = productRepo._categories

    init {
        viewModelScope.launch {
            productRepo.getCategories()
        }
    }






}