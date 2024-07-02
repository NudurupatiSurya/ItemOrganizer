package com.suryanudurupati.itemorganizer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.suryanudurupati.itemorganizer.model.GroupedItem
import com.suryanudurupati.itemorganizer.ui.itemlist.Header
import com.suryanudurupati.itemorganizer.ui.itemlist.ItemList
import com.suryanudurupati.itemorganizer.ui.itemlist.ItemsListUI
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
                    val viewModel: MainActivityViewModel = viewModel()
                    val groupedItems by viewModel.groupedItem.observeAsState()

                    // Load items when the UI is set
                    if (groupedItems == null) {
                        viewModel.loadItems()
                    }

                    groupedItems?.let {
                        ItemList(viewModel)
                    }
                }
            }
        }
    }
}

@Composable
fun CustomProgressIndicator() {
    Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
        CircularProgressIndicator()
    }
}

@Preview
@Composable
fun CustomProgressIndicatorPreview() {
    ItemOrganizerTheme {
        CustomProgressIndicator()
    }
}