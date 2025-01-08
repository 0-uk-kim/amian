package com.bokju.amian.placesearch.data

import com.bokju.amian.utils.model.DataError
import com.bokju.amian.utils.model.Result

interface RemotePlaceDataSource {
    suspend fun searchPlaces(
        query: String,
        coordinate: String? = null,
        filter: Int? = null,
        language: String? = null,
        page: Number? = null,
        count: Number? = null
    ): Result<GeocodingResponse, DataError.Remote>
}