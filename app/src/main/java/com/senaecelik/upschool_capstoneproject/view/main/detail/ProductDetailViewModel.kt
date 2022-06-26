package com.senaecelik.upschool_capstoneproject.view.main.detail

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.senaecelik.upschool_capstoneproject.data.model.Product
import com.senaecelik.upschool_capstoneproject.data.repository.ProductRepository

class ProductDetailViewModel(context: Context): ViewModel() {
    private var productRepo = ProductRepository(context)
    private var _isProductAddedBasket = MutableLiveData<Boolean>()

    val isProductAddedBasket: LiveData<Boolean>
        get() = _isProductAddedBasket

    init {
        _isProductAddedBasket = productRepo.isProductAddedBasket

    }
    fun addBasket(
        user: String,
        title: String,
        price: Double,
        desc: String,
        category: String,
        image:String,
        rate: Double,
        count: Int,
        saleState: Int,) {
        productRepo.addBasket(user, title, price, desc, category, image, rate, count, saleState)
    }


}