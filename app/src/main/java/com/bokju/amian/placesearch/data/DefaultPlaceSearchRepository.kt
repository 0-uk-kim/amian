package com.bokju.amian.placesearch.data

import com.bokju.amian.placesearch.domain.PlaceResult
import com.bokju.amian.placesearch.domain.PlaceSearchRepository
import com.bokju.amian.utils.map
import com.bokju.amian.utils.model.DataError
import com.bokju.amian.utils.model.Result

class DefaultPlaceSearchRepository(
    private val remotePlaceDataSource: RemotePlaceDataSource
) : PlaceSearchRepository {

    override suspend fun searchPlaces(
        query: String,
        coordinate: String?,
        filter: Int?,
        language: String?,
        page: Number?,
        count: Number?
    ): Result<List<PlaceResult>, DataError.Remote> {
        return remotePlaceDataSource
            .searchPlaces(
                query,
                coordinate,
                filter,
                language,
                page,
                count
            ).map { geocodingResponse ->
                geocodingResponse.toPlaceResults()
            }
    }
}

fun GeocodingResponse.toPlaceResults(): List<PlaceResult> {
    return addresses.map { address ->
        PlaceResult(
            title = address.roadAddress,
            address = address.jibunAddress,
            x = address.x,
            y = address.y
        )
    }
}