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

data class FilteredItems(
    val listId: List<Int>,
    val id: List<List<Int>>,
    val name: List<List<String?>>
)

typealias GroupedItems = List<GroupedItem>

data class GroupedItem(
    val listId: List<Int>,
    val listIds: List<Data>
)

data class Data(
    val ids: List<Int>,
    val names: List<String?>
)