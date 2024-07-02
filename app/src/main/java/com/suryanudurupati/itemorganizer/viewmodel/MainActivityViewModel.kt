package com.suryanudurupati.itemorganizer.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suryanudurupati.itemorganizer.Repository.ItemRepo
import com.suryanudurupati.itemorganizer.model.Data
import com.suryanudurupati.itemorganizer.model.FilteredItems
import com.suryanudurupati.itemorganizer.model.GroupedItem
import com.suryanudurupati.itemorganizer.model.Item
import com.suryanudurupati.itemorganizer.model.ItemModel
import com.suryanudurupati.itemorganizer.model.ItemsModel
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {

    private val repo = ItemRepo()
    private val _items = MutableLiveData<List<ItemModel>>()
    private val _isLoading = MutableLiveData<Boolean>(true)

    val items: LiveData<List<ItemModel>> get() = _items
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _groupedItems = MutableLiveData<GroupedItem>()
    val groupedItem: LiveData<GroupedItem> get() = _groupedItems

    private val _transformedItems = MutableLiveData<Map<Int, List<Item>>>()
    val transformedItems: LiveData<Map<Int, List<Item>>> get() = _transformedItems

    private val _filteredItems = MutableLiveData<Map<Int, List<Item>>>()
    val filteredItems: LiveData<Map<Int, List<Item>>> get() = _filteredItems

    private val _selectedListId = MutableLiveData<Int?>()
    val selectedListId: LiveData<Int?> get() = _selectedListId

    fun loadItems() {
        viewModelScope.launch {
            _isLoading.postValue(true)
            val result = repo.getItems()
            _items.postValue(result)
            groupItems(result)
            _isLoading.postValue(false)
        }
    }

    private fun groupItems(items: List<ItemModel>) {
        val filterItems2 = items.filter { it.name != null && it.name.isNotBlank() }
            .sortedWith(compareBy<ItemModel> { it.listId }.thenBy { it.id })

        val groupedItems = filterItems2.groupBy { it.listId }

        val listId = groupedItems.keys.toList()
        val listIds = groupedItems.values.map { itemList ->
            Data(
                ids = itemList.map { it.id },
                names = itemList.map { it.name }
            )
        }
        _groupedItems.postValue(GroupedItem(listId, listIds))
        _transformedItems.postValue(transformData(GroupedItem(listId, listIds)))
        _filteredItems.postValue(transformData(GroupedItem(listId, listIds)))
    }

    private fun transformData(groupedItem: GroupedItem): Map<Int, List<Item>> {
        val items = mutableListOf<Item>()
        groupedItem.listId.forEachIndexed { index, listId ->
            val data = groupedItem.listIds[index]
            data.ids.forEachIndexed { idx, id ->
                items.add(Item(listId = listId, id = id, name = data.names[idx]))
            }
        }
        return items.groupBy { it.listId }
    }

    fun onListIdSelected(listId: Int?) {
        _selectedListId.value = listId
        _filteredItems.value = if (listId == null) {
            _transformedItems.value
        } else {
            _transformedItems.value?.filter { it.key == listId }
        }
    }
}
