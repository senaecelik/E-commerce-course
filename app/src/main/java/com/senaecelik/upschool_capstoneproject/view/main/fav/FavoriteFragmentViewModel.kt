package com.senaecelik.upschool_capstoneproject.view.main.fav

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.senaecelik.upschool_capstoneproject.data.model.Product
import com.senaecelik.upschool_capstoneproject.data.repository.ProductRepository

class FavoriteFragmentViewModel(context: Context) : ViewModel() {

    private val productRepo = ProductRepository(context)

    private var _productBasket = MutableLiveData<List<Product>>()

    val productBasket: LiveData<List<Product>>
        get() = _productBasket

    private var _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    init {
        _isLoading = productRepo.isLoading
        getProductFav()
    }

    private fun getProductFav() {
        productRepo.productFav()
        _productBasket= productRepo.productBasketList
        _isLoading = productRepo.isLoading
    }

    fun deleteProductFromBasket(productId: Int) {
        productRepo.deleteProductFromFav(productId)
        getProductFav()
    }


}