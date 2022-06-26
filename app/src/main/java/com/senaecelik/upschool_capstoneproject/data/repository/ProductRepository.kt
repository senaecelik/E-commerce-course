package com.senaecelik.upschool_capstoneproject.data.repository

import com.senaecelik.upschool_capstoneproject.data.model.Product
import android.content.Context
import android.content.LocusId
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.senaecelik.upschool_capstoneproject.ApiUtils
import com.senaecelik.upschool_capstoneproject.common.CRUDResponse
import com.senaecelik.upschool_capstoneproject.data.roomdatabase.ProductFavDaoInterface
import com.senaecelik.upschool_capstoneproject.data.roomdatabase.ProductFavRoomDatabase
import com.senaecelik.upschool_capstoneproject.data.service.ProductApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductRepository(context: Context) {

    var productList = MutableLiveData<List<Product>>()
    var basketList = MutableLiveData<List<Product>>()
    var categoryList = MutableLiveData<List<String>>()
    var isLoading = MutableLiveData<Boolean>()
    var isProductAddedBasket = MutableLiveData<Boolean>()
    private val totalPrice = MutableLiveData<Double>()
    var productBasketList = MutableLiveData<List<Product>>()

    init {
        totalPrice.value = 10.0
    }


    private val productDIF: ProductApiInterface = ApiUtils.getProductDAOInterface()

    private val productFavDAOInterface: ProductFavDaoInterface? =
        ProductFavRoomDatabase.productFavRoomDatabase(context)?.productBasketDAOInterface()

    fun getTotalPrice(): MutableLiveData<Double> {
        return totalPrice
    }

    fun product() {
        isLoading.value = true
        productDIF.allProduct("senacelik").enqueue(object : Callback<List<Product>> {
            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {

                response.body()?.let {
                    productList.value = it
                    isLoading.value = false
                } ?: run {
                    isLoading.value = false
                }

            }

            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                t.localizedMessage?.toString()?.let { Log.e("Product Failure", it) }
                isLoading.value = false
            }
        })
    }

    fun addBasket(user: String,
                     title: String,
                     price: Double,
                     desc: String,
                     category: String,
                     image:String,
                     rate: Double,
                     count: Int,
                     saleState: Int,
                       ) {
        productDIF.addToBag(user, title, price, desc, category, image, rate, count, saleState).enqueue(object : Callback<CRUDResponse> {
            override fun onResponse(call: Call<CRUDResponse>, response: Response<CRUDResponse>) {
                Log.e("response", response.body()!!.success.toString())
                Log.e("mesaj", response.body()!!.message)
            }

            override fun onFailure(call: Call<CRUDResponse>, t: Throwable) {
                Log.e("response", "başarısız")
            }

        })

    }

    fun deleteBag(id: Int){
        isLoading.value = true
        productDIF.deleteBasket(id).enqueue(object : Callback<CRUDResponse> {
            override fun onResponse(call: Call<CRUDResponse>, response: Response<CRUDResponse>) {
                Log.e("response", response.body()!!.success.toString())
                Log.e("mesaj", response.body()!!.message)
            }

            override fun onFailure(call: Call<CRUDResponse>, t: Throwable) {
                Log.e("response", "başarısız")
            }

        })
    }


    fun getBagProducts(user: String) {
        isLoading.value = true
        productDIF.getBagProductByUser(user).enqueue(object : Callback<List<Product>> {
            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {

                response.body()?.let {
                    basketList.value = it
                    isLoading.value = false
                } ?: run {
                    isLoading.value = false
                }

            }

            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                t.localizedMessage?.toString()?.let { Log.e("Product Failure", it) }
                isLoading.value = false
            }
        })
    }

    fun getCategory() {
        isLoading.value = true
        productDIF.getCategoriesByUser("senacelik").enqueue(object : Callback<List<String>> {
            override fun onResponse(
                call: Call<List<String>>,
                response: Response<List<String>>,
            ) {

                response.body()?.let {
                    categoryList.value = it
                    System.out.println(it)
                    isLoading.value = false
                } ?: run {
                    isLoading.value = false
                }

            }

            override fun onFailure(call: Call<List<String>>, t: Throwable) {
                t.localizedMessage?.toString()?.let { Log.e("Books Failure", it) }
                isLoading.value = false
            }
        })
    }

    fun productFav() {
        isLoading.value = true
        productFavDAOInterface?.getProductFav()?.let {
            productBasketList.value = it
            isLoading.value = false
        } ?: run {
            isLoading.value = false
        }
    }


    fun addProductToFav(productModel: Product) {

        productFavDAOInterface?.getProductNamesFav()?.let {

            isProductAddedBasket.value = if (it.contains(productModel.productName).not()) {
                productFavDAOInterface.addProductFav(productModel)
                true
            } else {
                false
            }
        }
    }

    fun deleteProductFromFav(productId: Int) {
        productFavDAOInterface?.deleteProductWithId(productId)
    }

    fun clearFav() {
        productFavDAOInterface?.clearFav()
    }

}

