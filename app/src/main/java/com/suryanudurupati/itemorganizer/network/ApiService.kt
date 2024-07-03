package com.suryanudurupati.itemorganizer.network

import com.suryanudurupati.itemorganizer.model.ItemsModel
import retrofit2.http.GET

interface ApiService {

    @GET("/hiring.json")
    suspend fun getItems(): ItemsModel
}