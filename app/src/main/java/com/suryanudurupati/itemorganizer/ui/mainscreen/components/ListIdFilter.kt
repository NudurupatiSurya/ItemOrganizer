package com.suryanudurupati.itemorganizer.ui.mainscreen.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ListIdFilter(listIds: List<Int>, selectedListId: Int?, onListIdSelected: (Int?) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf("All")}

    Box(modifier = Modifier.wrapContentSize(), contentAlignment = Alignment.Center) {
        Column {
            OutlinedButton(onClick = { expanded = true }) {
                Row {
                    Text(text = selectedOption)
                    Spacer(modifier = Modifier.padding(horizontal = 5.dp))
                    Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = "Filter")
                }
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                DropdownMenuItem(text = { Text("All") }, onClick = {
                    onListIdSelected(null)
                    expanded = false
                    selectedOption = "All"
                })
                listIds.forEach { listId ->
                    DropdownMenuItem(text = { Text(listId.toString()) }, onClick = {
                        onListIdSelected(listId)
                        expanded = false
                        selectedOption = listId.toString()
                    })
                }
            }
        }
    }
}