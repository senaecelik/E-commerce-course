package com.senaecelik.upschool_capstoneproject.view.main.home

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.senaecelik.upschool_capstoneproject.data.model.Product
import com.senaecelik.upschool_capstoneproject.data.repository.ProductRepository

class HomeViewModel(context: Context) : ViewModel() {

    private var productRepo = ProductRepository(context)
    private var _productList = MutableLiveData<List<Product>>()
    private var _isProductAddedBasket = MutableLiveData<Boolean>()

    val productList: LiveData<List<Product>>
        get() = _productList

    val isProductAddedBasket: LiveData<Boolean>
        get() = _isProductAddedBasket

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    init {
        _isLoading = productRepo.isLoading
        getProduct()
    }

    private fun getProduct() {
        productRepo.product()
        _productList = productRepo.productList
        _isLoading = productRepo.isLoading
    }
    fun addProductToFav(productModel: Product) {
        productRepo.addProductToFav(productModel)
    }



}


