package com.bokju.amian.navigation

import android.Manifest
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.bokju.amian.placelist.PlaceListAction
import com.bokju.amian.placelist.PlaceListScreen
import com.bokju.amian.placesearch.PlaceSearchScreen
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun AmianNavHost() {
    val navController = rememberNavController()
    val permissions = listOf(
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION,
    )
    val permissionState = rememberMultiplePermissionsState(permissions) {
        navController.navigate(Route.PlaceSearch)
    }

    NavHost(
        navController = navController,
        startDestination = Route.AmianGraph
    ) {
        navigation<Route.AmianGraph>(
            startDestination = Route.PlaceList
        ) {
            composable<Route.PlaceList> {
                PlaceListScreen { action ->
                    when (action) {
                        is PlaceListAction.OnPlaceAddClick -> {
                            permissionState.launchMultiplePermissionRequest()
                        }
                    }
                }
            }

            composable<Route.PlaceSearch> {
                PlaceSearchScreen()
            }
        }
    }
}