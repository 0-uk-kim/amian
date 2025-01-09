package com.bokju.amian.placesearch.presentation

sealed interface PlaceSearchAction {
    data class UpdateCurrentLocation(val longitude: Double, val latitude: Double) :
        PlaceSearchAction

    data class OnSearchQueryChange(val query: String) : PlaceSearchAction
}