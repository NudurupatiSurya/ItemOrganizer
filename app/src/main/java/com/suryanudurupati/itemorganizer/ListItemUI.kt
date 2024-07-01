package com.suryanudurupati.itemorganizer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.suryanudurupati.itemorganizer.ui.theme.ItemOrganizerTheme

//TODO:: Convert this to data class

@Composable
fun ListItemUI(
    modifier: Modifier = Modifier,
    listIds: List<Int>,
    ids: List<List<Int>>,
    names: List<List<String>>
) {
    var height by remember { mutableStateOf(50.dp) }
    Row(modifier = modifier.fillMaxWidth()) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            HeaderText(text = "List ID", modifier = Modifier.padding(horizontal = 10.dp))
            LazyColumn() {
                items(listIds.size) { index ->
                    Row(modifier = Modifier.height(height)) {
                        ListIdText(listId = listIds[index].toString())
                    }
                }
            }
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            HeaderText(text = "ID", modifier = Modifier.padding(horizontal = 25.dp).onGloballyPositioned { layoutCoordinates ->
                height = layoutCoordinates.size.height.dp
            })
            LazyColumn {
                items(ids.size) { index ->
                    ItemRow(item = ids[index].map { it.toString() })
                }
            }
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            HeaderText(text = "Name", modifier = Modifier.padding(horizontal = 25.dp))
            LazyColumn {
                items(ids.size) { index ->
                    ItemRow(item = names[index])
                }
            }
        }
    }
}

@Composable
fun ItemRow(modifier: Modifier = Modifier, item: List<String>){
    Column {
        for (i in item.indices){
            Row {
                Text(item[i])
            }
        }

    }
}

@Composable
fun ListIdText(modifier: Modifier = Modifier, listId: String) {
    Column(modifier = Modifier.padding(horizontal = 10.dp)) {
        Text(listId, modifier = modifier, fontWeight = FontWeight.Bold)
    }
}

@Preview
@Composable
fun DataItemUIPreview() {
    ItemOrganizerTheme {
        val mockListId = listOf(1, 2)
        val mockId = listOf(listOf(753, 754, 755), listOf(855, 856, 857))
        val mockName = listOf(
            listOf("Item 753", "Item 754", "Item 755"),
            listOf("Item 855", "Item 856", "Item 857")
        )

        Column {
            ListItemUI(listIds = mockListId, ids = mockId, names = mockName)
        }
    }
}