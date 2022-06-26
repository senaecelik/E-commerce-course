package com.senaecelik.upschool_capstoneproject.data.roomdatabase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.senaecelik.upschool_capstoneproject.data.model.Product

@Dao
interface ProductFavDaoInterface {

    @Insert
    fun addProductFav(productBasketRoomModel: Product)

    @Query("SELECT * FROM productFavDatabase")
    fun getProductFav(): List<Product>?

    @Query("SELECT productName FROM productFavDatabase")
    fun getProductNamesFav(): List<String>?

    @Query("DELETE FROM productFavDatabase WHERE id = :idInput")
    fun deleteProductWithId(idInput: Int)

    @Query("DELETE FROM productFavDatabase")
    fun clearFav()

}