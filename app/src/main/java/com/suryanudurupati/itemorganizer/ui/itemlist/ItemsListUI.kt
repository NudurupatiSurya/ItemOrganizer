package com.suryanudurupati.itemorganizer.ui.itemlist

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.suryanudurupati.itemorganizer.model.Data
import com.suryanudurupati.itemorganizer.model.GroupedItem
import com.suryanudurupati.itemorganizer.ui.theme.ItemOrganizerTheme

@Composable
fun ItemsListUI(
    modifier: Modifier = Modifier,
    groupedItem: GroupedItem
) {
    var listId by remember {
        mutableStateOf("1")
    }
    Column {
        Header()
        Row(horizontalArrangement = Arrangement.SpaceEvenly) {
            ListIdText(listId = listId)
            LazyColumn(modifier.fillMaxWidth()) {
                items(groupedItem.listId.size) { index ->
                    Box(modifier = Modifier.fillMaxWidth()) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            listId = groupedItem.listId[index].toString()
                            Item(data = groupedItem.listIds[index], modifier = Modifier.padding(10.dp))
                        }
                    }
                    Spacer(Modifier.padding(10.dp))
                }
            }
        }
    }

}

@Composable
fun Item(
    data: Data,
    modifier: Modifier = Modifier
) {
    Column {
        for(i in data.ids.indices){
            Row {
                Text(text = data.ids[i].toString())
                Text(text = data.names[i].toString())
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


@Preview
@Composable
fun ItemsListUIPreview() {
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
        ItemsListUI(groupedItem = groupedItem)
    }
}