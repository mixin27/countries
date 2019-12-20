package com.norm.countries.remote.entity

import com.norm.countries.remote.network.NullToEmptyString
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Create by Kyaw Zayar Tun on 20/12/2019.
 */
@JsonClass(generateAdapter = true)
data class CountryResponse(
    @Json(name = "name")
    @NullToEmptyString
    val name: String?
)