package com.suryanudurupati.itemorganizer

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import com.suryanudurupati.itemorganizer.ui.theme.ItemOrganizerTheme

//TODO:: Convert function params to data class

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListItemUI(
    modifier: Modifier = Modifier,
    listIds: List<Int>,
    ids: List<List<Int>>,
    names: List<List<String>>
) {
    var height by remember { mutableIntStateOf(100) }
    Row(
        modifier = modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
            stickyHeader {
                HeaderText(text = "List ID", modifier = modifier.padding(horizontal = 10.dp))
            }
            items(listIds.size) { index ->
                Row(modifier = modifier.height((index + 2) * height.dp)) {
                    ListIdText(listId = listIds[index].toString())
                }
            }
        }
        Row(modifier = modifier.fillMaxSize(), horizontalArrangement = Arrangement.SpaceEvenly) {
            LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
                stickyHeader {
                    HeaderText(text = "ID", modifier = modifier
                        .padding(horizontal = 10.dp)
                        .onGloballyPositioned { layoutCoordinates ->
                            height = layoutCoordinates.size.height
                        })
                }
                items(ids.size) { index ->
                    ItemRow(item = ids[index].map { it.toString() })

                    Spacer(modifier = Modifier.padding(10.dp))
                }
            }

            LazyColumn(horizontalAlignment = Alignment.CenterHorizontally) {
                stickyHeader {
                    HeaderText(text = "Name", modifier = modifier.padding(horizontal = 10.dp))
                }
                items(ids.size) { index ->
                    ItemRow(item = names[index])
                    Spacer(modifier = Modifier.padding(10.dp))
                }
            }
        }
    }
}

@Composable
fun ItemRow(modifier: Modifier = Modifier, item: List<String>) {
    Column {
        for (i in item.indices) {
            Row {
                Text(item[i], modifier = modifier.padding(5.dp))
            }
        }

    }
}

@Composable
fun ListIdText(modifier: Modifier = Modifier, listId: String) {
    Column(modifier = Modifier.padding(10.dp)) {
        Text(listId, modifier = modifier, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun HeaderText(modifier: Modifier = Modifier, text: String) {
    Column(modifier = Modifier.padding(horizontal = 10.dp)) {
        Text(text, modifier = modifier, fontWeight = FontWeight.Bold, fontStyle = FontStyle.Italic)
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