package com.suryanudurupati.itemorganizer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.suryanudurupati.itemorganizer.ui.theme.ItemOrganizerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ItemOrganizerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val mockListId = listOf(1, 2)
    val mockId = listOf(listOf(753, 754, 755), listOf(855, 856, 857))
    val mockName = listOf(
        listOf("Item 753", "Item 754", "Item 755"),
        listOf("Item 855", "Item 856", "Item 857")
    )
    Column {
        HeaderUI()
        Column {
            for (i in mockListId.indices) {
                ListItemUI(listId = mockListId[i], ids = mockId[i], names = mockName[i])
                Divider()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ItemOrganizerTheme {
        Greeting("Android")
    }
}