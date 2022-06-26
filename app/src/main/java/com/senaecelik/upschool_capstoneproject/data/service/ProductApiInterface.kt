package com.senaecelik.upschool_capstoneproject.data.service

import com.senaecelik.upschool_capstoneproject.common.CRUDResponse
import com.senaecelik.upschool_capstoneproject.data.model.Product
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ProductApiInterface {

    @POST("get_products_by_user.php")
    @FormUrlEncoded
    fun allProduct(
        @Field("user") user: String
    ): Call<List<Product>>

    @POST("add_product.php")
    @FormUrlEncoded
    fun addProduct(
        @Field("id") id: Int,
        @Field("user") user: String,
        @Field("title") title: String,
        @Field("price") price: Double,
        @Field("description") description: String,
        @Field("category") category: String,
        @Field("image") image: String,
        @Field("rate") rate: Double,
        @Field("sale_state") saleState: Int,
    ):Call<CRUDResponse>

    @POST("delete_from_bag.php")
    @FormUrlEncoded
    fun deleteBasket(
        @Field("id") id: Int,
    ): Call<CRUDResponse>

    @POST("delete_from_bag.php")
    @FormUrlEncoded
    fun deleteAllBasket(
        @Field("user") user: String,
    ): Call<CRUDResponse>

    @POST("add_to_bag.php")
    @FormUrlEncoded
    fun addToBag(
        @Field("user") user: String,
        @Field("title") title: String,
        @Field("price") price: Double,
        @Field("description") description: String,
        @Field("category") category: String,
        @Field("image") image: String,
        @Field("rate") rate: Double,
        @Field("count") count: Int,
        @Field("sale_state") saleState: Int,
    ):Call<CRUDResponse>

    @POST("get_sale_products_by_user.php")
    @FormUrlEncoded
    fun getSaleState(
        @Field("id") id: String,
        @Field("sale_state") saleState: Int
    ): Call<List<Product>>

    @POST("get_bag_products_by_user.php")
    @FormUrlEncoded
    fun getBagProductByUser(
        @Field("user") user: String
    ): Call<List<Product>>

    @POST("get_categories_by_user.php")
    @FormUrlEncoded
    fun getCategoriesByUser(
        @Field("user") user: String,
    ): Call<List<String>>

    @POST("get_products_by_user_and_category.php")
    @FormUrlEncoded
    fun getProductByUserAndCategory(
        @Field("user") user: String,
        @Field("category") category: String
    ): Call<List<Product>>




}