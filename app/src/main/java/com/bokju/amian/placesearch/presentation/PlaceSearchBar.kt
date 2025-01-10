package com.bokju.amian.placesearch.presentation

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation
import com.bokju.amian.ui.theme.AmianTheme
import com.bokju.amian.utils.DarkLightPreview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaceSearchBar(
    query: String,
    onQueryChange: (String) -> Unit
) {
    BasicTextField(
        value = query,
        onValueChange = onQueryChange,
        singleLine = true,
        decorationBox = { innerTextField ->
            TextFieldDefaults.DecorationBox(
                value = query,
                innerTextField = innerTextField,
                enabled = true,
                singleLine = true,
                visualTransformation = VisualTransformation.None,
                interactionSource = remember { MutableInteractionSource() },
                leadingIcon = {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                },
                trailingIcon = {
                    if (query.isNotEmpty()) {
                        Icon(Icons.Default.Clear, contentDescription = null)
                    }
                }
            )
        },
        modifier = Modifier.fillMaxWidth()
    )
}

@DarkLightPreview
@Composable
fun PlaceSearchBarPreview() {
    AmianTheme {
        PlaceSearchBar(
            query = "서울",
            onQueryChange = {}
        )
    }
}