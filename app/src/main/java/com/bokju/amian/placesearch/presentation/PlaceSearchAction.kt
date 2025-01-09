package com.bokju.amian.placesearch.presentation

sealed interface PlaceSearchAction {
    data class OnSearchQueryChange(val query: String): PlaceSearchAction
}