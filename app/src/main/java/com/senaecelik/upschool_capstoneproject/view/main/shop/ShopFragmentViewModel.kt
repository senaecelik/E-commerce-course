package com.senaecelik.upschool_capstoneproject.view.main.shop

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.senaecelik.upschool_capstoneproject.data.model.Product
import com.senaecelik.upschool_capstoneproject.data.repository.ProductRepository

class ShopFragmentViewModel(context: Context) : ViewModel() {

    private var productRepo = ProductRepository(context)
    private var _productList = MutableLiveData<List<Product>>()
    private var _categoryList = MutableLiveData<List<String>>()

    val productList: LiveData<List<Product>>
        get() = _productList

    val categoryList: LiveData<List<String>>
        get() = _categoryList

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    init {
        _isLoading = productRepo.isLoading
        getCategory()
    }

    private fun getCategory() {
        productRepo.getCategory()
        _categoryList = productRepo.categoryList
        _isLoading = productRepo.isLoading
    }


}

