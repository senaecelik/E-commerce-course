package com.senaecelik.upschool_capstoneproject.view.success


import android.content.Context
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.senaecelik.upschool_capstoneproject.data.model.Product
import com.senaecelik.upschool_capstoneproject.data.repository.ProductRepository

class SuccessFragmentViewModel(context: Context) : ViewModel() {

    private val bagRepo = ProductRepository(context)

    private var _productBasket = MutableLiveData<List<Product>>()
    val productBasket: LiveData<List<Product>>
        get() = _productBasket

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    init {
        _isLoading = bagRepo.isLoading
    }

}