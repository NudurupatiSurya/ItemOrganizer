package com.suryanudurupati.itemorganizer.ui.itemlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.suryanudurupati.itemorganizer.model.GroupedItem

@Composable
fun HeaderText(modifier: Modifier = Modifier, text: String) {
    Column(modifier = Modifier.padding(horizontal = 10.dp)) {
        Text(text, modifier = modifier, fontWeight = FontWeight.Bold, fontStyle = FontStyle.Italic)
    }
}

@Composable
fun Header(
    modifier: Modifier = Modifier,
    groupedItem: GroupedItem
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Column {
            HeaderText(text = "List ID")
        }
        HeaderText(text = "ID")
        HeaderText(text = "Name")
    }
}