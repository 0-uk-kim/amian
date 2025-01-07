package com.bokju.amian.placelist

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun PlaceListScreen(
    onAction: (PlaceListAction) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        PlaceListContent(
            onAction = onAction,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        )
    }

}