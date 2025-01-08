package com.bokju.amian.placesearch.data

import kotlinx.serialization.Serializable

@Serializable
data class GeocodingResponse(
    val status: String,
    val meta: Meta,
    val addresses: List<Address>,
    val errorMessage: String?
)

@Serializable
data class Meta(
    val totalCount: Int,
    val page: Int,
    val count: Int
)

@Serializable
data class Address(
    val roadAddress: String,
    val jibunAddress: String,
    val englishAddress: String,
    val addressElements: List<AddressElement>,
    val x: String,
    val y: String,
    val distance: Double
)

@Serializable
data class AddressElement(
    val types: List<String>,
    val longName: String,
    val shortName: String,
    val code: String
)
