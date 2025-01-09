package com.bokju.amian.placesearch.presentation

import com.bokju.amian.placesearch.domain.PlaceResult
import com.bokju.amian.utils.UiText

data class PlaceSearchState(
    val searchQuery: String = "",
    val isLoading: Boolean = false,
    val searchResults: List<PlaceResult> = emptyList(),
    val errorMessage: UiText? = null
)