package com.senaecelik.upschool_capstoneproject.data.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIService {

    companion object {
        fun getClient(baseUrl: String): Retrofit {
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }

}