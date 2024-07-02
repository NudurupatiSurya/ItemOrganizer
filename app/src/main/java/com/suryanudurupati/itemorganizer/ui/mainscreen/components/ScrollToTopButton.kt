package com.suryanudurupati.itemorganizer.ui.mainscreen.components

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun ScrollToTopButton(coroutineScope: CoroutineScope, listState: LazyListState, modifier: Modifier = Modifier) {
    FloatingActionButton(
        onClick = {
            coroutineScope.launch {
                listState.animateScrollToItem(0)
            }
        },
        modifier = modifier
    ) {
        Icon(imageVector = Icons.Default.KeyboardArrowUp, contentDescription = "Tap to Scroll Up")
    }
}