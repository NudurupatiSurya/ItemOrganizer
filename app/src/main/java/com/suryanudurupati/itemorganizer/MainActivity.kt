package com.suryanudurupati.itemorganizer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposableTarget
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.suryanudurupati.itemorganizer.model.FilteredItems
import com.suryanudurupati.itemorganizer.ui.theme.ItemOrganizerTheme
import com.suryanudurupati.itemorganizer.viewmodel.MainActivityViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ItemOrganizerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen(viewModel: MainActivityViewModel = MainActivityViewModel()){

    LaunchedEffect(Unit) {
        viewModel.loadItems()
    }

    val isLoading by viewModel.isLoading.observeAsState(false)

    if (isLoading) {
        CustomProgressIndicator()
    } else {
        val filteredItems by viewModel.filteredItems.observeAsState(
            FilteredItems(
                listId = emptyList(),
                id = emptyList(),
                name = emptyList()
            )
        )

        ListItemUI(filteredItems = FilteredItems(filteredItems.listId, filteredItems.id, filteredItems.name))
    }
}


@Composable
fun CustomProgressIndicator() {
    Column(verticalArrangement = Arrangement.Center) {
        Row(horizontalArrangement = Arrangement.Center) {
            CircularProgressIndicator()
        }
    }
}

@Preview
@Composable
fun CustomProgressIndicatorPreview() {
    ItemOrganizerTheme {
        CustomProgressIndicator()
    }
}