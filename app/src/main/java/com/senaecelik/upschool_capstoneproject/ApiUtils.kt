package com.senaecelik.upschool_capstoneproject

import com.senaecelik.upschool_capstoneproject.common.Constants.BASE_URL
import com.senaecelik.upschool_capstoneproject.data.service.APIService
import com.senaecelik.upschool_capstoneproject.data.service.ProductApiInterface

class ApiUtils {
    companion object {

        fun getProductDAOInterface(): ProductApiInterface {
            return APIService.getClient(BASE_URL).create(ProductApiInterface::class.java)
        }
    }
}