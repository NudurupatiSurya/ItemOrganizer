package com.suryanudurupati.itemorganizer.ui.itemlist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.suryanudurupati.itemorganizer.ui.theme.ItemOrganizerTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Search() {
    var text by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }
    var items = remember {
        mutableStateListOf("adf", "dev")
    }
    SearchBar(query = text,
        onQueryChange = {
            text = it
        },
        onSearch = {
            active = false
        },
        active = active,
        onActiveChange = {
            active = it
        },
        placeholder = {
            Text("Search")
        },
        leadingIcon = {
            Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
        },
        trailingIcon = {
            if (active) {
                Icon(
                    modifier = Modifier.clickable {
                        if(text.isNotEmpty()){
                            text = ""
                        } else {
                            active = false
                        }
                    }, imageVector = Icons.Default.Close,
                    contentDescription = "Search"
                )
            }
        }
    ) {
        items.forEach {
            Row(Modifier.padding(14.dp)) {
                Text(text = it)
            }
        }
    }
}

@Preview
@Composable
fun SearchPreview() {
    ItemOrganizerTheme {
        Search()
    }
}