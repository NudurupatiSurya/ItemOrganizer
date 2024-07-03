package com.suryanudurupati.itemorganizer.repository

import com.suryanudurupati.itemorganizer.model.ItemsModel
import com.suryanudurupati.itemorganizer.network.RetrofitObject

class ItemRepo {

    private val apiService = RetrofitObject.apiService

    suspend fun getItems(): ItemsModel {
        return apiService.getItems()
    }
}