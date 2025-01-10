package com.bokju.amian.placesearch.presentation

import android.Manifest
import android.content.pm.PackageManager
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
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