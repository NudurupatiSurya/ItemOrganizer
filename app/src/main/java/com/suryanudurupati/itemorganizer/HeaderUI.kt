package com.suryanudurupati.itemorganizer

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.suryanudurupati.itemorganizer.ui.theme.ItemOrganizerTheme

@Composable
fun HeaderUI(modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(5.dp)) {
        Row {
            HeaderText(text = "List ID", modifier = modifier.padding(horizontal = 25.dp))
            HeaderText(text = "ID", modifier = modifier.padding(horizontal = 25.dp))
            HeaderText(text = "Name", modifier = modifier.padding(horizontal = 25.dp))
            Spacer(modifier = Modifier.height(4.dp))
        }
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
fun HeaderUIPreview() {
    ItemOrganizerTheme {
        HeaderUI()
    }
}