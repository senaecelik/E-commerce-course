package com.senaecelik.upschool_capstoneproject.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import com.google.gson.annotations.SerializedName

@Entity(tableName = "productFavDatabase" )
data class Product(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    @SerializedName("id")
    var id: Int,

    @ColumnInfo(name = "user")
    @SerializedName("user")
    var user: String,

    @ColumnInfo(name = "productName")
    @SerializedName("title")
    val productName: String,

    @ColumnInfo(name = "productPrice")
    @SerializedName("price")
    val productPrice: Double,

    @ColumnInfo(name = "productCategory")
    @SerializedName("category")
    val productCategory: String,

    @ColumnInfo(name = "productImg")
    @SerializedName("image")
    val productImage: String,

    @ColumnInfo(name = "productDescription")
    @SerializedName("description")
    val productDescription: String,

    @ColumnInfo(name = "productRate")
    @SerializedName("rate")
    val productRate: Double,

    @ColumnInfo(name = "productCount")
    @SerializedName("count")
    val productCount: Int,

    @ColumnInfo(name = "productSaleState")
    @SerializedName("sale_state")
    val saleState: Int,
) :Serializable{

}