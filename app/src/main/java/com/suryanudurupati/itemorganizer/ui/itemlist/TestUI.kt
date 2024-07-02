package com.suryanudurupati.itemorganizer.ui.itemlist

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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

@Composable
fun TestUI(
    modifier: Modifier = Modifier,
    groupedItem: GroupedItem
){
    var listId by remember { mutableStateOf("") }
    Row {
        ListIdText(listId = listId.toString())
        LazyColumn(Modifier.fillMaxWidth()) {
            items(groupedItem.listId.size) { index ->
                Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                    listId = groupedItem.listId[index].toString()
                    Column {
                        for(i in groupedItem.listIds[index].ids.indices) {
                            val data = groupedItem.listIds[index]
                            Row {
                                Text(text = data.ids[i].toString(), modifier = Modifier.padding(horizontal = 25.dp, vertical = 10.dp))
                                Text(text = data.names[i].toString(), modifier = Modifier.padding(horizontal = 25.dp, vertical = 10.dp))
                            }
                        }
                        Spacer(Modifier.padding(10.dp))
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ItemList(viewModel: MainActivityViewModel = viewModel()) {
    val transformedItems by viewModel.transformedItems.observeAsState(emptyMap())
    val listState = rememberLazyListState()

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column {
            Header()
            LazyColumn(state = listState, modifier = Modifier.padding(16.dp)) {
                transformedItems.forEach { (listId, items) ->
                    stickyHeader {
                        ListIdRow(listId)
                    }
                    items(items) { item ->
                        ItemRow(item)
                    }
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


@Preview
@Composable
fun TestUIPreview() {
    ItemOrganizerTheme {
        val mockListId = listOf(1, 2)
        val mockId = listOf(listOf(753, 754, 755), listOf(855, 856, 857))
        val mockName = listOf(
            listOf("Item 753", "Item 754", "Item 755"),
            listOf("Item 855", "Item 856", "Item 857")
        )

        val dataItems = mockId.zip(mockName).map { (ids, names) ->
            Data(ids = ids, names = names)
        }

        val groupedItem = GroupedItem(listId = mockListId, listIds = dataItems)
        TestUI(groupedItem = groupedItem)
    }
}