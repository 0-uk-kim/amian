package com.bokju.amian.placesearch.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNotNull
import org.koin.androidx.compose.koinViewModel

@Composable
fun PlaceSearchScreen(
    placeSearchViewModel: PlaceSearchViewModel = koinViewModel()
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val state by placeSearchViewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            PlaceSearchBar(
                query = state.searchQuery,
                onQueryChange = {
                    placeSearchViewModel.onAction(PlaceSearchAction.OnSearchQueryChange(it))
                }
            )
        },
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        }
    ) { innerPadding ->
        PlaceSearchContent(
            state = state,
            onAction = placeSearchViewModel::onAction,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        )
    }

    val context = LocalContext.current
    LaunchedEffect(Unit) {
        snapshotFlow { state.errorMessage }
            .distinctUntilChanged()
            .filterNotNull()
            .collect {
                snackbarHostState.showSnackbar(it.asString(context))
            }
    }
}