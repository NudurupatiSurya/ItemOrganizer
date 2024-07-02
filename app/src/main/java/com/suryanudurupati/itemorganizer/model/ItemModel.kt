package com.suryanudurupati.itemorganizer.model

import com.google.gson.annotations.SerializedName

typealias ItemsModel = List<ItemModel>


data class ItemModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("listId")
    val listId: Int,
    @SerializedName("name")
    val name: String?
)

data class GroupedItem(
    val listId: List<Int>,
    val listIds: List<Data>
)

data class Data(
    val ids: List<Int>,
    val names: List<String?>
)

data class Item(val listId: Int, val id: Int, val name: String?)