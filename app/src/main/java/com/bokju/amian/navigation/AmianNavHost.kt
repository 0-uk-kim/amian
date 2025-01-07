package com.bokju.amian.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.bokju.amian.placelist.PlaceListScreen

@Composable
fun AmianNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Route.PlaceList
    ) {
        navigation<Route.PlaceList>(
            startDestination = Route.PlaceList
        ) {
            composable<Route.PlaceList> {
                PlaceListScreen()
            }

            composable<Route.PlaceDetail> {

            }
        }
    }
}