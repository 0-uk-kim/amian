package com.bokju.amian.placesearch.data

import com.bokju.amian.BuildConfig
import com.bokju.amian.utils.model.DataError
import com.bokju.amian.utils.model.Result
import com.bokju.amian.utils.safeCall
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter

private const val BASE_URL = "https://naveropenapi.apigw.ntruss.com/map-geocode/v2"

class KtorRemotePlaceDataSource(
    private val httpClient: HttpClient
) : RemotePlaceDataSource {

    override suspend fun searchPlaces(
        query: String,
        coordinate: String?,
        filter: Int?,
        language: String?,
        page: Number?,
        count: Number?
    ): Result<GeocodingResponse, DataError.Remote> {
        return safeCall<GeocodingResponse> {
            httpClient.get(urlString = BASE_URL) {
                header("X-NCP-APIGW-API-KEY-ID", BuildConfig.XNCPAPIGWAPIKEYID)
                header("X-NCP-APIGW-API-KEY", BuildConfig.XNCPAPIGWAPIKEY)
                parameter("query", query)
                coordinate?.let { parameter("coordinate", it) }
                filter?.let { parameter("filter", it) }
                language?.let { parameter("language", it) }
                page?.let { parameter("page", it) }
                count?.let { parameter("count", it) }
            }
        }
    }
}