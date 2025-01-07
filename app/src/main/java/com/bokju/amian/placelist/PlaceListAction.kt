package com.bokju.amian.placelist

sealed interface PlaceListAction {
    data object OnPlaceAddClick: PlaceListAction
}