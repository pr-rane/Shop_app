package com.example.shop_app.data.models.entities


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Geolocation(
    @Json(name = "lat")
    val lat: String,
    @Json(name = "long")
    val long: String
)