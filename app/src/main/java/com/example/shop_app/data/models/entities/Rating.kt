package com.example.shop_app.data.models.entities


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = false)
data class Rating(
    @Json(name = "count")
    val count: Int,
    @Json(name = "rate")
    val rate: Double
)