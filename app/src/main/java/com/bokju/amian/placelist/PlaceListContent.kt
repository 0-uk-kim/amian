package com.bokju.amian.placelist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bokju.amian.ui.theme.AmianTheme
import com.bokju.amian.utils.DarkLightPreview
import com.bokju.amian.utils.dashedModifier

@Composable
fun PlaceListContent(
    onAction: (PlaceListAction) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        modifier = modifier
    ) {
        item {
            AddPlaceButton(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .padding(8.dp)
                    .aspectRatio(1f)
                    .then(
                        dashedModifier(
                            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.6f),
                            radius = 8.dp
                        )
                    )
                    .clickable {
                        onAction(PlaceListAction.OnPlaceAddClick)
                    }
            )
        }
    }
}

@DarkLightPreview
@Composable
fun PlaceListContentPreview() {
    AmianTheme {
        PlaceListContent(
            onAction = {},
            modifier = Modifier.background(MaterialTheme.colorScheme.background)
        )
    }
}