package com.bokju.amian.placesearch.domain

import com.bokju.amian.utils.model.DataError
import com.bokju.amian.utils.model.Result

interface PlaceSearchRepository {
    suspend fun searchPlaces(
        query: String,
        coordinate: String? = null,
        filter: Int? = null,
        language: String? = null,
        page: Number? = null,
        count: Number? = null
    ): Result<List<PlaceResult>, DataError.Remote>
}