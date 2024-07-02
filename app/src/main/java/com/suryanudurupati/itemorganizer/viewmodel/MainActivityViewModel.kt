package com.suryanudurupati.itemorganizer.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suryanudurupati.itemorganizer.Repository.ItemRepo
import com.suryanudurupati.itemorganizer.model.Data
import com.suryanudurupati.itemorganizer.model.FilteredItems
import com.suryanudurupati.itemorganizer.model.ItemModel
import com.suryanudurupati.itemorganizer.model.ItemsModel
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {

    private val repo = ItemRepo()
    private val _items = MutableLiveData<ItemsModel>()
    private val _isLoading = MutableLiveData<Boolean>(true)

    val items: LiveData<ItemsModel> get() = _items
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _filteredItems = MutableLiveData<FilteredItems>()
    val filteredItems: LiveData<FilteredItems> get() = _filteredItems

    fun loadItems() {
        viewModelScope.launch {
            _isLoading.postValue(true)
            val result = repo.getItems()
            _items.postValue(result)
            filterData(result)
            _isLoading.postValue(false)
        }
    }

    private fun filterData(items: List<ItemModel>) {
        val filterItems = items.filter{ !it.name.isNullOrBlank() }.groupBy {it.listId}

        val filterItems2 = items.filter{it.name != null && it.name.isNotBlank()}
            .sortedWith(compareBy<ItemModel> { it.listId }.thenBy { it.id })

        val groupedItems = filterItems2.groupBy { it.listId }

        val listId = groupedItems.keys.toList()
        val listIds = groupedItems.values.map { itemList ->
            Data(
                ids = itemList.map { it.id },
                names = itemList.map { it.name }
            )
        }

//        val groupedItem = GroupedItem(listId = listId, listIds = listIds)

//        val listIds = filterItems.keys.toList()
//        val ids = filterItems.values.map{it.map{ item -> item.id }}
//        val names = filterItems.values.map{it.map{ item -> item.name }}

        _filteredItems.postValue(FilteredItems(listId = listIds, id = ids, name = names))
    }
}
