package com.suryanudurupati.itemorganizer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.suryanudurupati.itemorganizer.model.FilteredItems
import com.suryanudurupati.itemorganizer.ui.theme.ItemOrganizerTheme

@Composable
fun ItemsListUI(
    modifier: Modifier = Modifier,
    filteredItems: FilteredItems
){
    Column() {
        Header()
        for (i in filteredItems.listId.indices){
            Item(filteredItems.listId[i], filteredItems.id[i], filteredItems.name[i])
        }
    }
}

@Composable
fun Item(
    listId: Int,
    ids: List<Int>,
    names: List<String?>
){
    Row(
        Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        ListIdText(listId = listId.toString())
        LazyColumn {
            items(ids.size) { index ->
                Text(ids[index].toString())
            }
        }
        LazyColumn {
            items(names.size) { index ->
                Text(names[index].toString())
            }
        }
    }
}

@Preview
@Composable
fun ItemsListUIPreview(){
    ItemOrganizerTheme {
        val mockListId = listOf(1, 2)
        val mockId = listOf(listOf(753, 754, 755), listOf(855, 856, 857))
        val mockName = listOf(
            listOf("Item 753", "Item 754", "Item 755"),
            listOf("Item 855", "Item 856", "Item 857")
        )
        Column {
            ItemsListUI(filteredItems = FilteredItems(mockListId, mockId, mockName))
        }
    }
}