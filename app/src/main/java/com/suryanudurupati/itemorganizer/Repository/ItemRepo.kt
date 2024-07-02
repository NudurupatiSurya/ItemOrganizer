package com.suryanudurupati.itemorganizer.Repository

import com.suryanudurupati.itemorganizer.model.ItemModel
import com.suryanudurupati.itemorganizer.model.ItemsModel
import com.suryanudurupati.itemorganizer.utils.RetrofitObject

class ItemRepo {

    private val apiService = RetrofitObject.apiService

    suspend fun getItems(): ItemsModel {
        return apiService.getItems()
    }
}