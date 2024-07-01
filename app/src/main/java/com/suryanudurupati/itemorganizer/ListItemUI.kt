package com.suryanudurupati.itemorganizer

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.suryanudurupati.itemorganizer.ui.theme.ItemOrganizerTheme

//TODO:: Convert this to data class

@Composable
fun ListItemUI(
    modifier: Modifier = Modifier,
    listId: Int,
    ids: List<Int>,
    names: List<String>
) {
    Column(modifier = modifier.padding(10.dp)) {
        Row {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                ListIdUI(listId = listId.toString())
            }
            LazyColumn(userScrollEnabled = true) {
                items(ids.size) { index ->
                    Row {
                        Text(text = ids[index].toString(), modifier = modifier.padding(horizontal = 25.dp))
                        Text(text = names[index], modifier = modifier.padding(horizontal = 25.dp))
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                }
            }
        }
    }
}

@Composable
fun ListIdUI(modifier: Modifier = Modifier, listId: String) {
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
            HeaderUI()
            for (i in mockListId.indices) {
                ListItemUI(listId = mockListId[i], ids = mockId[i], names = mockName[i])
                Divider()
            }
        }
    }
}