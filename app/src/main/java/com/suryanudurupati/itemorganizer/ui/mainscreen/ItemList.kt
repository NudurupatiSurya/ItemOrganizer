package com.suryanudurupati.itemorganizer.ui.mainscreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.suryanudurupati.itemorganizer.ui.mainscreen.components.Header
import com.suryanudurupati.itemorganizer.ui.mainscreen.components.ListIdFilter
import com.suryanudurupati.itemorganizer.ui.mainscreen.components.ScrollToTopButton
import com.suryanudurupati.itemorganizer.ui.mainscreen.components.SearchBar
import com.suryanudurupati.itemorganizer.viewmodel.MainActivityViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ItemList(viewModel: MainActivityViewModel = viewModel()) {
    val filteredItems by viewModel.filteredItems.observeAsState(emptyMap())
    val selectedListId by viewModel.selectedListId.observeAsState()
    val searchQuery by viewModel.searchQuery.observeAsState("")
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val listIds = filteredItems.keys.toList()
    val showButton by remember {
        derivedStateOf {
            listState.firstVisibleItemIndex > 0
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                SearchBar(searchQuery) { query ->
                    viewModel.onSearchQueryChanged(query)
                }
                Spacer(modifier = Modifier.width(16.dp))
                ListIdFilter(listIds) { listId ->
                    viewModel.onListIdSelected(listId)
                }
            }
            Header()
            LazyColumn(state = listState, modifier = Modifier.padding(16.dp)) {
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
            ScrollToTopButton(
                coroutineScope = coroutineScope,
                listState = listState,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(50.dp)
            )
        }
    }
}
