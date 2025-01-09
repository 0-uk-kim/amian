package com.bokju.amian.placesearch

import android.Manifest
import android.content.pm.PackageManager
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bokju.amian.placesearch.presentation.PlaceSearchAction
import com.bokju.amian.placesearch.presentation.PlaceSearchViewModel
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.tasks.await
import org.koin.androidx.compose.koinViewModel

@Composable
fun PlaceSearchScreen(
    placeSearchViewModel: PlaceSearchViewModel = koinViewModel()
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
            placeSearchViewModel.onAction(
                PlaceSearchAction.UpdateCurrentLocation(
                    longitude = location.longitude,
                    latitude = location.latitude
                )
            )
        }
    }

    val state = placeSearchViewModel.state.collectAsStateWithLifecycle()
    println("${state.value}")
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Place Detail Screen")
    }
}