package com.bokju.amian.placesearch.presentation

import android.Manifest
import android.content.pm.PackageManager
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.tasks.await

@Composable
fun PlaceSearchContent(
    state: PlaceSearchState,
    modifier: Modifier = Modifier,
    onAction: (PlaceSearchAction) -> Unit = {}
) {
    Box(modifier = modifier) {
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else if (state.searchResults.isNotEmpty()) {
            LazyColumn(modifier = modifier) {
                items(state.searchResults) { placeResult ->
                    Text(text = placeResult.title)
                }
            }
        }
    }

    val context = LocalContext.current
    val locationProviderClient = remember {
        LocationServices.getFusedLocationProviderClient(context)
    }

    LaunchedEffect(Unit) {
        val isFineLocationGranted = ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val isCoarseLocationGranted = ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        if (isFineLocationGranted || isCoarseLocationGranted) {
            val location = locationProviderClient.lastLocation.await()
            onAction(
                PlaceSearchAction.UpdateCurrentLocation(
                    longitude = location.longitude,
                    latitude = location.latitude
                )
            )
        }
    }

}