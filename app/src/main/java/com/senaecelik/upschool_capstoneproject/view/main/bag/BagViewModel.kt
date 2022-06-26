package com.senaecelik.upschool_capstoneproject.view.main.bag

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.senaecelik.upschool_capstoneproject.data.model.Product
import com.senaecelik.upschool_capstoneproject.data.repository.ProductRepository

class BagViewModel(context: Context) : ViewModel() {

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
    fun deleteProductFromBasket(productId: Int, user: String) {
        bagRepo.deleteBag(productId)
        getBagProduct(user)
    }

    fun getBagProduct(user:String){
        bagRepo.getBagProducts(user)
        _productBasket=bagRepo.basketList
        _isLoading = bagRepo.isLoading
    }

}