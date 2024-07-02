package com.suryanudurupati.itemorganizer.ui.itemlist

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.suryanudurupati.itemorganizer.model.Data
import com.suryanudurupati.itemorganizer.model.GroupedItem
import com.suryanudurupati.itemorganizer.model.Item
import com.suryanudurupati.itemorganizer.ui.theme.ItemOrganizerTheme
import com.suryanudurupati.itemorganizer.viewmodel.MainActivityViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ItemList(viewModel: MainActivityViewModel = viewModel()) {
    val transformedItems by viewModel.transformedItems.observeAsState(emptyMap())
    val filteredItems by viewModel.filteredItems.observeAsState(emptyMap())
    val selectedListId by viewModel.selectedListId.observeAsState()
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val listIds = transformedItems.keys.toList()
    val showButton by remember {
        derivedStateOf {
            listState.firstVisibleItemIndex > 0
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            Header()
            ListIdFilter(listIds, selectedListId) { listId ->
                viewModel.onListIdSelected(listId)
            }
            LazyColumn(state = listState, modifier = Modifier.padding(16.dp), contentPadding = PaddingValues(bottom = 50.dp)) {
                filteredItems.forEach { (listId, items) ->
                    stickyHeader {
                        ListIdRow(listId)
                    }
                    items(items) { item ->
                        ItemRow(item)
                    }
                }
            }
        }
        if (showButton) {
            FloatingActionButton(
                onClick = {
                    coroutineScope.launch {
                        listState.animateScrollToItem(0)
                    }
                },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
            ) {
                Icon(imageVector = Icons.Default.KeyboardArrowUp, contentDescription = "Tap to Scroll Up")
            }
        }
    }
}

@Composable
fun ListIdFilter(listIds: List<Int>, selectedListId: Int?, onListIdSelected: (Int?) -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Column {
            TextButton(onClick = { expanded = true }) {
                Text(text = "Filter by List ID")
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                DropdownMenuItem(text = { Text("All") }, onClick = {
                    onListIdSelected(null)
                    expanded = false
                })
                listIds.forEach { listId ->
                    DropdownMenuItem(text = {Text(listId.toString())}, onClick = {
                        onListIdSelected(listId)
                        expanded = false
                    })
                }
            }
        }
    }
}

@Composable
fun Header() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "List ID",
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center
        )
        Text(
            text = "ID",
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center
        )
        Text(
            text = "Name",
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun ListIdRow(listId: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = listId.toString(),
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center
        )
        Text(
            text = "",
            modifier = Modifier.weight(1f)
        )
        Text(
            text = "",
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun ItemRow(item: Item) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "",
            modifier = Modifier.weight(1f)
        ) // Placeholder for List ID column
        Text(
            text = item.id.toString(),
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center
        )
        Text(
            text = item.name ?: "N/A",
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center
        )
    }
}