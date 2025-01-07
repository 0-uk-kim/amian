package com.bokju.amian.navigation

import kotlinx.serialization.Serializable

sealed interface Route {

    @Serializable
    data object AmianGraph: Route

    @Serializable
    data object PlaceList: Route

    @Serializable
    data object PlaceSearch: Route

    @Serializable
    data object PlaceDetail: Route
}