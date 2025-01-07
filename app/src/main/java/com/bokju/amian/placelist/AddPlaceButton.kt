package com.bokju.amian.placelist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bokju.amian.R
import com.bokju.amian.ui.theme.AmianTheme
import com.bokju.amian.utils.DarkLightPreview
import com.bokju.amian.utils.dashedModifier

@Composable
fun AddPlaceButton(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = stringResource(R.string.add_place_button),
            modifier = Modifier.size(48.dp),
            tint = MaterialTheme.colorScheme.primary
        )
    }
}

@DarkLightPreview
@Composable
fun PlaceAdditionItemPreview() {
    AmianTheme {
        AddPlaceButton(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .then(
                    dashedModifier(
                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.6f),
                        radius = 8.dp
                    )
                )
        )
    }
}